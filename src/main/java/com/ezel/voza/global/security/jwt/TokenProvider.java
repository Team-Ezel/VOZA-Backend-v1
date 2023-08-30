package com.ezel.voza.global.security.jwt;

import com.ezel.voza.global.security.jwt.properties.JwtProperties;
import com.ezel.voza.global.security.jwt.properties.TokenTimeProperties;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

import com.ezel.voza.global.security.exception.TokenExpiredException;
import com.ezel.voza.global.security.exception.TokenNotVaildException;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final UserDetailsService userDetailsService;
    private final TokenTimeProperties tokenTimeProperties;
    private final JwtProperties jwtProperties;

    @AllArgsConstructor
    private enum TokenObject {
        ACCESS_TYPE("access"),
        REFRESH_TYPE("refresh"),
        TOKEN_PREFIX("Bearer "),
        AUTHORITY("authority");
        final String value;
    }

    public ZonedDateTime accessExpiredTime() {

        return ZonedDateTime.now().plusSeconds(tokenTimeProperties.getAccessTime());
    }

    public ZonedDateTime refreshExpiredTime() {

        return ZonedDateTime.now().plusSeconds(tokenTimeProperties.getRefreshTime());
    }

    public String generateAccessToken(String email) {

        return generateToken(email, TokenObject.ACCESS_TYPE.value, jwtProperties.getAccessSecret(), tokenTimeProperties.getAccessTime());
    }

    public String generateRefreshToken(String email) {

        return generateToken(email, TokenObject.REFRESH_TYPE.value, jwtProperties.getRefreshSecret(), tokenTimeProperties.getRefreshTime());
    }

    public Authentication authentication(String token) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(getTokenSubject(token, jwtProperties.getAccessSecret()));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null) {
            return null;
        }

        return parseToken(token);
    }

    public String exactEmailFromRefreshToken(String refresh) {
        return getTokenSubject(refresh, jwtProperties.getRefreshSecret());
    }

    public String generateToken(String email, String type, Key secret, Long exp) {

        final Claims claims = Jwts.claims().setSubject(email);

        claims.put("type", type);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(secret, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String parseToken(String token) {

        if(token.startsWith(TokenObject.TOKEN_PREFIX.value)) {
            return token.replace(TokenObject.TOKEN_PREFIX.value, "");

        } else {
            return null;
        }
    }

    private Claims getTokenBody(String token, Key secret) {

        try {

            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {

            throw new TokenExpiredException();

        } catch (JwtException e) {

            throw new TokenNotVaildException();
        }
    }

    private String getTokenSubject(String token, Key secret) {

        return getTokenBody(token, secret).getSubject();
    }
}
