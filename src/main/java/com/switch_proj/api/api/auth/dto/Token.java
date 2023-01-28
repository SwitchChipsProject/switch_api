package com.switch_proj.api.api.auth.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Token {
    private String accessToken;
    private String refreshToken;
    private String key;
    @Builder
    public Token(String accessToken, String refreshToken, String key) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.key = key;
    }


}
