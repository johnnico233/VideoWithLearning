package com.yosoro.video.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=((HttpServletRequest) request).getSession();
        if(session.getAttribute("videoUser")!=null){
            chain.doFilter(request,response);
        }else{
            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
