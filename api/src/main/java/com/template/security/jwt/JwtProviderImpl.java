package com.template.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

public class JwtProviderImpl implements JwtProvider{

    @Override
    public String createToken(Authentication authentication) {
        return null;
    }

    @Override
    public Authentication getAuthentication(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
