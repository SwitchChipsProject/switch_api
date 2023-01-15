package com.switch_proj.api.api.auth.utils;

import com.switch_proj.api.api.auth.domain.TokenDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    //jwt token을 생성,인증,권한 부여 등의 다양한 기능을 제공하는 클래스
    @Value("${jwt.secret}")
    private  String jwtSecret;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private static Integer expirationDateInMinute;


    @Value("${jwt.expirationDateInMinute}")
    public void setExpirationDateInMinute(Integer value) {
        expirationDateInMinute = value;
    }

    private static Integer refreshTokenExpirationDateInMinute;
    @Value("${jwt.refreshTokenExpirationDateInMinute}")
    public void setRefreshTokenExpirationDateInMinute(Integer value) {
        refreshTokenExpirationDateInMinute = value;
    }

    // jwt 토큰 생성
    public String generateToken(String userId, TokenDetails tokenDetails){
        // jwt payload에 저장되는 정보단위
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role",tokenDetails.getRole());
         return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationDateInMinute * 1000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }
    //jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
    // request의 header에서 token값을 가져온다.
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    //토큰에서 회원 정보 추출
    public String getUserId(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }
    // Jwt Token의 유효성 및 만료 기간 검사
    public boolean validateToken(String jwtToken) {
        try {
            // 토큰을 파싱하고 난 후 발생하는 exception들을 캐치, 문제가 생기면 false, 정상이면 true를 return
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(jwtToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}