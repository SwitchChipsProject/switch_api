package com.switch_proj.api.api.image.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity {
    private Long imageId;
    private String deletedYn;
    private String originalFileName;
    private Long fileSize;
    private String fileName;
    private String endPoint;
    protected LocalDateTime createdAt;
    protected LocalDateTime deletedAt;
}
