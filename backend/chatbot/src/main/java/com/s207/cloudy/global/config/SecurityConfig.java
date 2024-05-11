package com.s207.cloudy.global.config;

import com.s207.cloudy.global.auth.filter.ExceptionHandlerFilter;
import com.s207.cloudy.global.auth.filter.JwtAuthenticationFilter;
import com.s207.cloudy.global.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)->{
//                    request.requestMatchers(antMatcher("/**")).permitAll();
                    request.requestMatchers(antMatcher("/**")).permitAll();
                    request.requestMatchers(antMatcher("/h2-console/**")).permitAll();
                })
                .headers(headers->headers.frameOptions(frameOptions->frameOptions.disable()))
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter(), JwtAuthenticationFilter.class); // ExceptionHandlerFilter 추가


        return http.build();
    }





    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {

        return new JwtAuthenticationFilter(jwtService);
    }


    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter(){
        return new ExceptionHandlerFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(null);
        return new ProviderManager(provider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}


