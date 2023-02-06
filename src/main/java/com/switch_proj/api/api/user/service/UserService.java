package com.switch_proj.api.api.user.service;

import com.switch_proj.api.api.auth.enums.AuthEnums;
import com.switch_proj.api.api.auth.utils.SecurityUtil;
import com.switch_proj.api.api.exception.dto.BadRequestException;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import com.switch_proj.api.api.user.dto.User;
import com.switch_proj.api.api.user.dto.UserLocation;
import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.entity.UserLocationEntity;
import com.switch_proj.api.api.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    @Transactional
    public void saveUser(User user) {
        if (userMapper.existByUserEmail(user.getEmail()))
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_INVALID, "이미 가입되어있는 이메일입니다.");

        if (userMapper.existByNickname(user.getNickname()))
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_INVALID, "중복된 닉네임입니다.");

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
        if (!userEntity.isCertificatedYn())
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_INVALID , "이미 인증된 메일입니다");
        userMapper.updateEmailCertificationState(userEntity.getUserId());
    }

    @Transactional(readOnly = true)
    public UserEntity getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(userMapper::findByUserEmail)
                .orElseThrow(()-> new BadRequestException(ExceptionEnum.RESPONSE_NOT_FOUND));
    }

}
