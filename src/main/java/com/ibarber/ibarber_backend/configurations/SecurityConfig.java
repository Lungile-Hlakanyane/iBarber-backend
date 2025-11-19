package com.ibarber.ibarber_backend.configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;


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
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "api/users/user",
                                "/api/questions/**",
                                "/api/ratings/**",
                                "/api/support/**",
                                "/api/slots/**",
                                "/api/users/verify-users",
                                "/api/users/request-reset",
                                "/api/users/reset-password",
                                "/api/auth/**",
                                "/api/users/register",
                                "/api/users/all",
                                "/api/users/barbers",
                                "/api/slots/barber-id",
                                "/api/posts/**",
                                "/api/comments/**",
                                "/api/likes/**",
                                "/api/chats/**",
                                "/api/portfolios/**",
                                "/api/announcements/**",
                                "/api/report-user/**",
                                "/uploads/**",
                                "/ws/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow both local development & deployed URLs
        config.setAllowedOriginPatterns(List.of(
                "http://localhost:8100",
                "http://localhost:4200",
                "http://13.49.76.153:*",
                "http://13.49.76.153:8080",
                "https://13.49.76.153:*",
                "https://*.duckdns.org",
                "https://*.amazonaws.com"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return (CorsConfigurationSource) source;
    }

//    @Bean
//    public HttpFirewall allowUrlEncodedNewlineHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowUrlEncodedNewline(true); // Allow %0A and %0D
//        return firewall;
//    }
}
