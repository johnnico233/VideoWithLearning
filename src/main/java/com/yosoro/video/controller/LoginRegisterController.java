package com.yosoro.video.controller;

import com.yosoro.video.domain.result.Result;
import com.yosoro.video.domain.result.ResultCode;
import com.yosoro.video.domain.user.LoginUser;
import com.yosoro.video.domain.user.PrivateUser;
import com.yosoro.video.domain.user.User;
import com.yosoro.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/createNewUser",method = RequestMethod.POST)
    public @ResponseBody
    Result createNewUser(@RequestBody User user){
        System.out.println(user);
        boolean result=userService.addNewUser(user);
        System.out.println(result);
        return new Result(result? ResultCode.SUCCESS:ResultCode.MAIL_EXIST,result?"注册成功":"邮箱已经被其他人注册了TAT");
    }

    @RequestMapping("/checkMail")
    public @ResponseBody Result checkMailExist(@RequestBody PrivateUser user, HttpSession httpSession){
        boolean result=userService.UserEmailExist(user.getMail(),httpSession);
        return new Result(result?ResultCode.SUCCESS:ResultCode.MAIL_EXIST,result?"邮箱可用":"邮箱已经被注册");
    }
    @RequestMapping("/login")
    public @ResponseBody Result checkPasswordValid(@RequestBody LoginUser user, HttpServletResponse response, HttpSession httpSession){
        System.out.println(user);
        boolean result=userService.checkPasswordValid(user,response,httpSession);
        return new Result(result?ResultCode.SUCCESS:ResultCode.PASSWORD_ERROR,result?"":"密码错误");
    }
}
