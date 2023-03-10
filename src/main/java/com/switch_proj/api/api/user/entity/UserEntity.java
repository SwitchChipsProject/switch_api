package com.switch_proj.api.api.user.entity;

import com.switch_proj.api.api.auth.enums.AuthEnums;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    private Long userId;
    private String password;
    private String nickname;
    private Long imageId;
    private String deletedYn;

    private AuthEnums.ROLE role;
    private String email;
    private boolean agreeYn;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String certificationCode;
    private boolean certificatedYn;

    @Builder
    public UserEntity(Long userId, String password, String nickname, Long imageId, String deletedYn, AuthEnums.ROLE role, String email, boolean agreeYn, String certificationCode, boolean certificatedYn) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.imageId = imageId;
        this.deletedYn = deletedYn;
        this.role = role;
        this.email = email;
        this.agreeYn = agreeYn;
        this.certificationCode = certificationCode;
        this.certificatedYn = certificatedYn;
    }

}
