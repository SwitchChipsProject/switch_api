package com.switch_proj.api.api.user.service;

import com.switch_proj.api.api.auth.domain.Token;
import com.switch_proj.api.api.auth.domain.TokenResponse;
import com.switch_proj.api.api.auth.enums.AuthEnums;
import com.switch_proj.api.api.auth.utils.JwtTokenProvider;
import com.switch_proj.api.api.user.domain.User;

import com.switch_proj.api.api.user.domain.UserLocation;
import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailSenderService emailSenderService;

    public TokenResponse login(User user, HttpServletResponse response){
        UserEntity userEntity = userMapper.findByUserEmail(user.getEmail());
        if(!passwordEncoder.matches(user.getPassword(),userEntity.getPassword()))
            throw new RuntimeException();

        Token accessToken = jwtTokenProvider.createAccessToken(userEntity);
        Token refreshToken =jwtTokenProvider.createRefreshToken(userEntity);
        jwtTokenProvider.setHeaderAccessToken(response, accessToken.getValue());
        return TokenResponse.builder()
                .token(accessToken.getValue())
                .user(userEntity)
                .build();
    }
    public void saveUser(User user) {
        //TODO : 예외처리 필요, 위치 정보 추가
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .role(AuthEnums.ROLE.ROLE_USER)
                .nickname(user.getNickname())
                .password(passwordEncoder.encode(user.getPassword()))
                .certificationCode(UUID.randomUUID().toString())
                .build();
        userMapper.saveUser(userEntity);
        emailSenderService.sendMail(userEntity);
    }
    public void certificationEmail(String termUuid){
        UserEntity userEntity = userMapper.findByCertificationCode(termUuid);
        userMapper.updateEmailCertificationState(userEntity.getUserId());

    }
}
