package com.switch_proj.api.api.user.dto;

import com.switch_proj.api.api.auth.enums.AuthEnums;


import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    private Long userId;
    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자리수여야 합니다.")
    private String password;
    @NotBlank(message = "이메일이 입력되지 않았습니다.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "닉네임이 입력되지 않았습니다.")
    private String nickname;
    private boolean agreeYn;
    @NotNull(message = "위치정보가 입력되지 않았습니다.")
    private UserLocation userLocation;

    @Builder
    public User(Long userId, String password, String email, String nickname, boolean agreeYn, UserLocation userLocation) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.agreeYn = agreeYn;
        this.userLocation = userLocation;
    }

    public static User fromEntity(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .build();
    }


}
