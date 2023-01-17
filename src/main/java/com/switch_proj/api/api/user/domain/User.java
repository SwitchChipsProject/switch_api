package com.switch_proj.api.api.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.switch_proj.api.api.auth.domain.Token;
import com.switch_proj.api.api.auth.enums.AuthEnums;
import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@Getter
public class User{
    private Long userId;
    private String password;
    private String email;
    private String nickname;
    private AuthEnums.ROLE role;
    private String agreeYn;
    private UserLocation userLocation;
    private String accessToken;



}
