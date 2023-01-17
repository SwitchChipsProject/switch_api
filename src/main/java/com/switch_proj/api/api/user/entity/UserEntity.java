package com.switch_proj.api.api.user.entity;

import com.switch_proj.api.api.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    private Long userId;
    private String password;
    private String nickname;
    private Long imageId;
    private String deletedYn;
    private String role;
    private String email;
    private String agreeYn;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

@Builder
    public UserEntity(Long userId, String password, String nickname, Long imageId, String deletedYn, String role, String email, String agreeYn) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.imageId = imageId;
        this.deletedYn = deletedYn;
        this.role = role;
        this.email = email;
        this.agreeYn = agreeYn;
    }
}
