package com.hotel.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("http://localhost:5173") // 允许的来源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true); // 允许发送凭据（如 cookies）
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //    告知系统static 当成 静态资源访问
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\avatars\\";
        registry.addResourceHandler("/assets/avatars/**").addResourceLocations("file:" + path);
    }
}
