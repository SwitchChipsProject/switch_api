package com.switch_proj.api.api.auth.utils;

import com.switch_proj.api.api.exception.dto.BadRequestException;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.expirationDateInMinute}")
    private long ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refreshTokenExpirationDateInMinute}")
    private long REFRESH_TOKEN_EXPIRE_TIME;
    @Value("${jwt.refreshSecret}")
    private String refreshSecret;
    @Value("${jwt.secret}")
    private String secret;
    private Key key;
    private Key refreshKey;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    }

    public String createAccessToken(Authentication authentication, String authorities) {
        long now = (new Date()).getTime();
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

        return accessToken;
    }

    public String createRefreshToken(Authentication authentication, String authorities) {
        long now = (new Date()).getTime();
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(refreshKey,SignatureAlgorithm.HS512)
                .compact();

        return refreshToken;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new BadRequestException(ExceptionEnum.RESPONSE_TOKEN_INVALID, "?????? ????????? ?????? ???????????????.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Authentication getRefreshAuthentication(String refreshToken) {
        Claims claims = parseClaims(refreshToken);
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new BadRequestException(ExceptionEnum.RESPONSE_TOKEN_INVALID, "?????? ????????? ?????? ???????????????.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }


    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private Claims parseRefreshClaims(String refreshToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("????????? JWT ???????????????.");
        } catch (ExpiredJwtException e) {
            logger.info("????????? JWT ???????????????.");
        } catch (UnsupportedJwtException e) {
            logger.info("???????????? ?????? JWT ???????????????.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT ????????? ?????????????????????.");
        }
        return false;
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(refreshToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("????????? JWT ???????????????.");
        } catch (ExpiredJwtException e) {
            logger.info("????????? JWT ???????????????.");
        } catch (UnsupportedJwtException e) {
            logger.info("???????????? ?????? JWT ???????????????.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT ????????? ?????????????????????.");
        }
        return false;
    }
}