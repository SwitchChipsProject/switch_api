package com.switch_proj.api.api.auth.domain;

import com.switch_proj.api.api.user.domain.User;
import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)

public class TokenResponse {
    private UserEntity user;
    private String token;

    @Builder
    public TokenResponse(UserEntity user, String token) {
        this.user = user;
        this.token = token;
    }
}
