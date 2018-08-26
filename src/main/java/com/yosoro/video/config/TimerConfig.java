package com.yosoro.video.config;

import com.yosoro.video.domain.timer.EmailTimer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class TimerConfig {
    @Bean
    public ConcurrentHashMap<String,EmailTimer> copyOnWriteArrayList(){
        ConcurrentHashMap<String,EmailTimer> map=new ConcurrentHashMap<>();
        return map;
    }
    @Bean
    public ThreadPoolTaskScheduler taskScheduler(ConcurrentHashMap<String,EmailTimer> map){
        ThreadPoolTaskScheduler scheduler=new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.initialize();
        scheduler.schedule(()->{
            Enumeration<String> keys=map.keys();
            while (keys.hasMoreElements()){
                String key=keys.nextElement();
                if(!map.get(key).isValid())
                    map.remove(key);
            }
        },new CronTrigger("0/30 * * * * ? "));
        return scheduler;
    }
}
