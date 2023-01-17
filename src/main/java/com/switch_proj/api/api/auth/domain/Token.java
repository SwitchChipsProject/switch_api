package com.switch_proj.api.api.auth.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Token {
    private Long expiredIn;
    private String value;
    private String key;
    @Builder
    public Token(String key, String value, Long expiredIn) {
        this.key = key;
        this.value = value;
        this.expiredIn = expiredIn;
    }
}
