package com.switch_proj.api.api.auth.filter;


import com.switch_proj.api.api.auth.utils.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     try {
         String path = request.getServletPath();
         if(path.startsWith("/api/auth/reissue")){
             filterChain.doFilter(request,response);
         }
         else
         {
             String accessToken = jwtTokenProvider.resolveAccessToken(request);
             boolean isVaild = jwtTokenProvider.validateToken(accessToken,request);
             if(StringUtils.hasText(accessToken)&& isVaild){
                 this.setAuthentication(accessToken);
             }
             filterChain.doFilter(request,response);
         }
     }
     catch (ExpiredJwtException e){
         return;
     }
    }
    private void setAuthentication(String token){
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
