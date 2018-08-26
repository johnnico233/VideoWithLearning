package com.yosoro.video.filter;

import com.yosoro.video.dao.UserMapper;
import com.yosoro.video.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CookieFilter implements javax.servlet.Filter{
    public CookieFilter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    private UserMapper userMapper;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession=((HttpServletRequest)request).getSession();
        if(httpSession.getAttribute("videoUser")==null){
            Cookie[] cookies =((HttpServletRequest)request).getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("videoUser")){
                        User user=userMapper.getUserById(Integer.valueOf(cookie.getValue()));
                        System.out.println(user);
                        httpSession.setAttribute("videoUser",user);
                        break;
                    }
                }
            }
        }
        chain.doFilter(request,response);
    }
    @Override
    public void destroy() {

    }
}
