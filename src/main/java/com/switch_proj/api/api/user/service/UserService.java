package com.switch_proj.api.api.user.service;

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

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        //TODO : 예외처리 필요, 위치 정보 추가
        if(user.getRole() == null){

        }
        UserEntity userEntity = User.toEntity(user);
        userMapper.saveUser(userEntity);
    }
}
