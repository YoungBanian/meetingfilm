package com.zhengrz.meetingfilm.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class BackendZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendZuulGatewayApplication.class, args);
    }

}
