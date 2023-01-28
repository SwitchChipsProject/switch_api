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
    private String agreeYn;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String certificationCode;
    private boolean isCertificatied;

    @Builder
    public UserEntity(Long userId, String password, String nickname, Long imageId, String deletedYn, AuthEnums.ROLE role, String email, String agreeYn, String certificationCode, boolean isCertificatied) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.imageId = imageId;
        this.deletedYn = deletedYn;
        this.role = role;
        this.email = email;
        this.agreeYn = agreeYn;
        this.certificationCode = certificationCode;
        this.isCertificatied = isCertificatied;
    }

}
