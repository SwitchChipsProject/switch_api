package com.switch_proj.api.api.user.dto;

import com.switch_proj.api.api.user.entity.UserLocationEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserLocation {
    private Float latitude;
    private Float longitude;
    private String address;
    private User userId;
    @Builder
    public UserLocation(Float latitude, Float longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
    public static UserLocation fromEntity(UserLocationEntity userLocationEntity){
        return UserLocation.builder()
                .longitude(userLocationEntity.getLongitude())
                .latitude(userLocationEntity.getLatitude())
                .address(userLocationEntity.getAddress())
                .build();
    }



}
