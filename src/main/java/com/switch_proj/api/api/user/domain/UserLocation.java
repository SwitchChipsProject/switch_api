package com.switch_proj.api.api.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserLocation {
    private Long latitude;
    private Long longtitude;
    private String address;
}
