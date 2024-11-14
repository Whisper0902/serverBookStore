package com.example.demo.Cord;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Cho phép tất cả các origin hoặc chỉ cho phép một origin cụ thể
        registry.addMapping("/**")  // Áp dụng cho tất cả các endpoint
                .allowedOrigins("http://localhost:63342")  // Chỉ cho phép truy cập từ localhost:63342
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Các phương thức HTTP được phép
                .allowedHeaders("*");  // Cho phép tất cả header
    }
}
