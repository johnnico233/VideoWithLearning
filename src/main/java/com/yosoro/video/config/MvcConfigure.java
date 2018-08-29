package com.yosoro.video.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigure implements WebMvcConfigurer{
    @Value("${physics-image-dir}")
    private String physicsImageDir;
    @Value("${virtual-image-dir}")
    private String virtualImageDir;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualImageDir+"**").addResourceLocations("file:"+physicsImageDir);
    }

}
