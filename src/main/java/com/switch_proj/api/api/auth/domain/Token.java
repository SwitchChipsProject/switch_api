package com.switch_proj.api.api.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Token {
    private Long expiredIn;
    private Long createdAt;
    private String accessToken;
    private String refreshToken;
    private String accessTokenId;
}
