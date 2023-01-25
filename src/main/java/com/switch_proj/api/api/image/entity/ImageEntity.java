package com.switch_proj.api.api.image.entity;

import lombok.AccessLevel;
import lombok.Builder;
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
    @Builder
    public ImageEntity(Long imageId, String originalFileName, Long fileSize, String fileName, String endPoint) {
        this.imageId = imageId;
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.endPoint = endPoint;
    }
}
