package com.switch_proj.api.api.user.domain;

import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    private Long userId;
    private String password;
    private String email;
    private String nickname;
    private String role;
    private PasswordEncoder passwordEncoder;

}
