package com.yosoro.video.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class MyBatisConfig {
    @Bean("sqlSessionFactory")
    public SqlSessionFactory factory() throws IOException{
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().
                build(new ClassPathResource("/config/myBatis/myBatis.xml").getInputStream());
        return factory;
    }
    @Bean
    public MapperScannerConfigurer configurer(){
        MapperScannerConfigurer configurer=new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.yosoro.video.dao");
        return configurer;
    }
}
