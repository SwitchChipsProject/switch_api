package com.switch_proj.api.api.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class UserLocation {
    private Long latitude;
    private Long longitude;
    private String address;
    private User userId;
}
