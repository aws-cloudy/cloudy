package com.s207.cloudy.global.config;

import com.s207.cloudy.global.auth.filter.JwtAuthenticationFilter;
import com.s207.cloudy.global.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;



    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        var config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addExposedHeader("accessToken");
        config.addExposedHeader("refreshToken");
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable);
       http.authorizeHttpRequests((request)->{
           request.requestMatchers(antMatcher("/api/v1/roadmaps/my/**")).authenticated();
           request.requestMatchers(antMatcher("/**")).permitAll();
           request.requestMatchers(antMatcher("/h2-console/**")).permitAll();
       });
    http.headers(headers->headers.frameOptions(frameOptions->frameOptions.disable()));
    http.addFilter(jwtAuthenticationFilter());

        return http.build();
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService);
    }


}


