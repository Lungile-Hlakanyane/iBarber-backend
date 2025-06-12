package com.ibarber.ibarber_backend.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/uploads/**")
                .allowedOrigins("http://localhost:8100", "http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*");

        registry.addMapping("/ws/**")
                .allowedOrigins("http://localhost:8100", "http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
