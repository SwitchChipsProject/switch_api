package com.switch_proj.api.api.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.switch_proj.api.api.user.domain.User;
import lombok.*;

@RequiredArgsConstructor
public class TokenResponse {
    private User user;
    private Token token;
}
