package com.yosoro.video.service;

import com.yosoro.video.dao.UserMapper;
import com.yosoro.video.domain.timer.EmailTimer;
import com.yosoro.video.domain.user.LoginUser;
import com.yosoro.video.domain.user.PrivateUser;
import com.yosoro.video.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConcurrentHashMap<String,EmailTimer> emailTimerMap;
    public boolean UserEmailExist(String email, HttpSession session){
        EmailTimer emailTimer;
        if((emailTimer=emailTimerMap.get(email))!=null){
            if(emailTimer.getSessionId().equals(session.getId())){
                emailTimer.setStartTime(System.currentTimeMillis());
                emailTimerMap.put(email,emailTimer);
                return true;
            }
            return false;
        }
        else{
            //参数life代表的是该email最多存活时间,以秒作为单位
            if(userMapper.checkUserExist(email)==0)
                return emailTimerMap.put(email,
                        new EmailTimer(email,System.currentTimeMillis(),30,session.getId()))==null;
            return false;
        }
    }
    public boolean addNewUser(User user){
        HashMap<String,Object> map=new HashMap<>();
        map.put("mail",user.getMail());
        map.put("password",user.getPassword());
        map.put("userName",user.getUserName());
        map.put("result",0);
        userMapper.addNewUser(map);
        return (int)map.get("result")>0;
    }
    public boolean checkPasswordValid(LoginUser user, HttpServletResponse response,HttpSession session){
        User privateUser=userMapper.checkLoginValid(user);
        session.setAttribute("videoUser",privateUser);
        if(privateUser!=null&&user.isRemember()){
            Cookie cookie=new Cookie("videoUser",String.valueOf(privateUser.getId()));
            cookie.setPath("/");
            cookie.setMaxAge(3600*24*7);
            response.addCookie(cookie);
        }
        return privateUser!=null;
    }
}
