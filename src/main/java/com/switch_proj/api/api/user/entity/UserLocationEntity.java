package com.switch_proj.api.api.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLocationEntity {

    private Long userLocationId;
    private Long latitude;
    private Long longtitude;
    private String address;
    private Long userId;
    protected LocalDateTime createdAt;
    protected LocalDateTime deletedAt;
    protected String deletedYn;
}
