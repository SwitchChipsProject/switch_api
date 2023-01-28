package com.switch_proj.api.api.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserLocation {
    private Float latitude;
    private Float longitude;
    private String address;
    private User userId;
}
