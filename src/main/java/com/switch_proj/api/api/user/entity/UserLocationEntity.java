package com.switch_proj.api.api.user.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLocationEntity {
    private Long userLocationId;
    private Float latitude;
    private Float longitude;
    private String address;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserLocationEntity(Long userLocationId, Float latitude, Float longitude, String address, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userLocationId = userLocationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
