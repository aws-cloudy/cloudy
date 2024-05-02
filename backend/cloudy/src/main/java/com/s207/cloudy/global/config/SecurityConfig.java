package com.s207.cloudy.global.config;


import com.s207.cloudy.domain.members.application.MemberService;
import com.s207.cloudy.domain.members.dao.MemberRepository;
import com.s207.cloudy.global.auth.filter.JwtAuthenticationFilter;
import com.s207.cloudy.global.auth.filter.MemberRegistryFilter;
import com.s207.cloudy.global.auth.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final MemberService memberService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests((request)->{
                   request.requestMatchers(antMatcher("/api/v1/my/**")).authenticated();
                   request.requestMatchers(antMatcher("/**")).permitAll();
                   request.requestMatchers(antMatcher("/h2-console/**")).permitAll();
                })
        .headers(headers->headers.frameOptions(frameOptions->frameOptions.disable()))
        .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(memberRegistryFilter(),JwtAuthenticationFilter.class);


        return http.build();
    }





    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {

        return new JwtAuthenticationFilter(jwtService);
    }


    @Bean
    public MemberRegistryFilter memberRegistryFilter(){
        return new MemberRegistryFilter(memberService);
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


