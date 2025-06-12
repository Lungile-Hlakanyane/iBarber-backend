package com.ibarber.ibarber_backend.configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/verify-otp",
                                "/api/auth/login",
                                "/api/auth/user",
                                "/api/users/**",
                                "/api/slots/**",
                                "/api/slots/create",
                                "/api/slots/unbooked/barber/**",
                                "/api/slots/barber-id",
                                "/api/users/all",
                                "/api/users/barbers",
                                "/api/posts",
                                "/api/posts/**",
                                "/uploads/**",
                                "/api/comments",
                                "/api/comments/**",
                                "/api/likes",
                                "/api/likes/**",
                                "/api/chats",
                                "/api/chats/**",
                                "/ws/**",
                                "/api/portfolios",
                                "/api/portfolios/**",
                                "/api/portfolios/upload",
                                "/uploads/**",
                                "/portfolio-images",
                                "/portfolio-images/**",
                                "/api/announcements",
                                "/api/announcements/**",
                                "/api/report-user",
                                "/api/report-user/**"
                        )
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {});
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("http://localhost:8100")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
