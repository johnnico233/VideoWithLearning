package com.yosoro.video.config;

import com.yosoro.video.dao.UserMapper;
import com.yosoro.video.filter.CookieFilter;
import com.yosoro.video.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Autowired
    private UserMapper userMapper;
    @Bean
    public FilterRegistrationBean cookieFilterRegistration(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new CookieFilter(userMapper));
        registrationBean.setOrder(3);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    @Bean FilterRegistrationBean loginFilterRegistration(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.setOrder(4);
        registrationBean.addUrlPatterns("/user/*");
        return registrationBean;
    }
}
