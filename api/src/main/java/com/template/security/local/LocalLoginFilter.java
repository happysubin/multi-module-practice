package com.template.security.local;

import com.template.security.jwt.JwtProvider;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LocalLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtProvider jwtProvider;

    public LocalLoginFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        super(new AntPathRequestMatcher("/login", "POST"), authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response
    ) throws AuthenticationException, IOException, ServletException {
        Authentication authentication = null;
        return this.getAuthenticationManager().authenticate(authentication);
    }
}
