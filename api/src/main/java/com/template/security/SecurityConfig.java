package com.template.security;


import com.template.core.domain.member.MemberRepository;
import com.template.security.jwt.JwtProvider;
import com.template.security.jwt.JwtProviderImpl;
import com.template.security.local.LocalAuthenticationProvider;
import com.template.security.local.LocalLoginFilter;
import com.template.security.local.LocalUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .formLogin().disable()
                .csrf().disable();


        http.authorizeRequests().antMatchers(HttpMethod.POST, "/members").permitAll();
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    LocalLoginFilter loginFilter() throws NoSuchAlgorithmException {
        return new LocalLoginFilter(authenticationManager(), jwtProvider());
    }

    @Bean
    AuthenticationManager authenticationManager() throws NoSuchAlgorithmException {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
        return new LocalAuthenticationProvider(userDetailsService(), passwordEncoder());
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new LocalUserDetailService(memberRepository);
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
