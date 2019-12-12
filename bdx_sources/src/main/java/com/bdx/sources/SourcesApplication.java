package com.bdx.sources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtInterceptorUtil;
import util.JwtUtil;

/**
 * @author: 磊大大
 * @date: 2019/12/11 14:19
 */
@SpringBootApplication
@EnableEurekaClient
public class SourcesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SourcesApplication.class, args);
    }

    @Bean
    public JwtInterceptorUtil jwtInterceptorUtil() {
        return new JwtInterceptorUtil();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
