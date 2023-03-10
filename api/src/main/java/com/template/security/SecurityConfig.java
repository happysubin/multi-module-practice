package com.template.security;


import com.template.security.jwt.JwtProvider;
import com.template.security.jwt.JwtProviderImpl;
import com.template.security.local.LocalAuthenticationProvider;
import com.template.security.local.LocalLoginFilter;
import com.template.security.local.LocalUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    LocalLoginFilter loginFilter(){
        return new LocalLoginFilter(authenticationManager(), jwtProvider());
    }

    @Bean
    AuthenticationManager authenticationManager(){
        return new ProviderManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
        return new LocalAuthenticationProvider(userDetailsService(), passwordEncoder());
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new LocalUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        return new BCryptPasswordEncoder(10, random);
    }

    @Bean
    JwtProvider jwtProvider(){
        return new JwtProviderImpl();
    }
}
