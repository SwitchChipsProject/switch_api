package com.switch_proj.api.api.user.dto;

import com.switch_proj.api.api.auth.enums.AuthEnums;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    private Long userId;
    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    private String password;
    @NotBlank(message = "이메일이 입력되지 않았습니다.")
    private String email;
    @NotBlank(message = "닉네임이 입력되지 않았습니다.")
    private String nickname;
    private AuthEnums.ROLE role;
    private String agreeYn;
    @NotNull(message = "위치정보가 입력되지 않았습니다.")
    private UserLocation userLocation;
    private String accessToken;
}
