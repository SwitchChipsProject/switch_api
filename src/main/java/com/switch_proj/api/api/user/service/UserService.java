package com.switch_proj.api.api.user.service;

import com.switch_proj.api.api.user.domain.User;

import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
