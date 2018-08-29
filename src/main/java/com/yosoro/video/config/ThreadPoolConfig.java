package com.yosoro.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService executorService(){
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        return executorService;
    }
}
