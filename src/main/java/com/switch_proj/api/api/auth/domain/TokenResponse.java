package com.switch_proj.api.api.auth.domain;

import com.switch_proj.api.api.user.domain.User;
import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)

public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    @Builder
    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
