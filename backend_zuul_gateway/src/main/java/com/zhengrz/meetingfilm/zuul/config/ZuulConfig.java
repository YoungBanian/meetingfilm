package com.zhengrz.meetingfilm.zuul.config;

import com.zhengrz.meetingfilm.zuul.filters.JWTFilter;
import com.zhengrz.meetingfilm.zuul.filters.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

//    @Bean
//    public MyFilter initMyFilter(){
//        return new MyFilter();
//    }

    @Bean
    public JWTFilter initJWTFilter(){
        return new JWTFilter();
    }
}
