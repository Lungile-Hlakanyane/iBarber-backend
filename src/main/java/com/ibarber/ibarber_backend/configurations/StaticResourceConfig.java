package com.ibarber.ibarber_backend.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDir = Paths.get("C:/Users/lungi/Downloads/ibarber-backend/ibarber-backend/uploads/portfolio-images").toUri().toString();
        registry.addResourceHandler("/portfolios-images/**")
                .addResourceLocations(uploadDir); // Map URL path to local file path
    }
}
