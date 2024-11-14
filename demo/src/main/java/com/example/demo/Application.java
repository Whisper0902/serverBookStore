package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Chú thích để Spring Boot biết đây là entry point
public class Application {

    public static void main(String[] args) {
        // Chạy ứng dụng Spring Boot
        SpringApplication.run(Application.class, args);
    }
}