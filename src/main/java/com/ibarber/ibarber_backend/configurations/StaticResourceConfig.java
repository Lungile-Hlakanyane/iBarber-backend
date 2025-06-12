package com.ibarber.ibarber_backend.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = Paths.get("uploads/portfolios-images").toFile().getAbsolutePath();
        registry.addResourceHandler("/portfolios-images/**")
                .addResourceLocations("file:" + absolutePath + "/");
    }
}
