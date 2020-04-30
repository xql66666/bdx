package com.bdx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.AesUtil;
import util.IdWorker;
import util.JwtInterceptorUtil;
import util.JwtUtil;

/**
 * @author: 磊大大
 * @date: 2019/10/12 16:59
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

    @Bean
    public AesUtil aesUtil() {
        return new AesUtil();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public JwtInterceptorUtil jwtInterceptorUtil() {
        return new JwtInterceptorUtil();
    }
}
