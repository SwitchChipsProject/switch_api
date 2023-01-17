package com.switch_proj.api.api.auth.domain;

import com.switch_proj.api.api.auth.enums.AuthEnums;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class TokenDetails {
    private Long userId;
    private String userEmail;
    private String password;
    private String role;
    private String refreshToken;

    @Builder
    public TokenDetails(Long userId, String userEmail, String password, String role, String refreshToken) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.password = password;
        this.role = role;
        this.refreshToken = refreshToken;
    }

}
