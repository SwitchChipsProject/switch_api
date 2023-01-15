package com.switch_proj.api.api.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.switch_proj.api.api.auth.enums.AuthEnums;
import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@Getter
public class User{
    private Long userId;
    private String password;
    private String email;
    private String nickname;
    private AuthEnums.ROLE role;
    private String agreeYn;
    private UserLocation userLocation;

    public static UserEntity toEntity(User user){
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .role(AuthEnums.ROLE.ROLE_USER)
                .build();
        return userEntity;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
