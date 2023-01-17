package com.switch_proj.api.api.user.service;

import com.switch_proj.api.api.auth.domain.TokenResponse;
import com.switch_proj.api.api.auth.enums.AuthEnums;
import com.switch_proj.api.api.auth.utils.JwtTokenProvider;
import com.switch_proj.api.api.exception.domain.BadRequestException;
import com.switch_proj.api.api.exception.domain.ExceptionEnum;
import com.switch_proj.api.api.user.domain.User;
import com.switch_proj.api.api.user.domain.UserLocation;
import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.entity.UserLocationEntity;
import com.switch_proj.api.api.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailSenderService emailSenderService;

    public TokenResponse login(User user) {
        //TODO : 예외처리
        // 이메일이 맞지 않을 경우 예외처리
        UserEntity userEntity = userMapper.findByUserEmail(user.getEmail());

        String accessToken = jwtTokenProvider.createAccessToken(userEntity);
        String refreshToken = jwtTokenProvider.createRefreshToken(userEntity);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void saveUser(User user) {
        if (userMapper.existByUserEmail(user.getEmail()))
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_INVALID, "이미 가입되어있는 이메일입니다.");

        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .role(AuthEnums.ROLE.ROLE_USER)
                .nickname(user.getNickname())
                .password(passwordEncoder.encode(user.getPassword()))
                .certificationCode(UUID.randomUUID().toString())
                .build();
        userMapper.saveUser(userEntity);

        Long userId = userEntity.getUserId();
        saveUserLocation(user.getUserLocation(), userId);
        emailSenderService.sendMail(userEntity);
    }

    public void saveUserLocation(UserLocation userLocation, Long userId) {
        UserLocationEntity userLocationEntity = UserLocationEntity.builder()
                .latitude(userLocation.getLatitude())
                .longitude(userLocation.getLongitude())
                .address(userLocation.getAddress())
                .userId(userId)
                .build();
        userMapper.saveUserLocation(userLocationEntity);
    }

    @Transactional
    public void certificationEmail(String termUuid) {
        UserEntity userEntity = userMapper.findByCertificationCode(termUuid);
        if (!userEntity.isEmailCertification())
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_INVALID, "인증되지 않은 이메일입니다.");
        userMapper.updateEmailCertificationState(userEntity.getUserId());
    }
}
