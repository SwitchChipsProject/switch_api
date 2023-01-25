package com.switch_proj.api.api.image.dto;

import com.switch_proj.api.api.image.entity.ImageEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Image {
    private Long imageId;
    private String originalFileName;
    private Long fileSize;
    private String fileName;
    private String endPoint;

    @Builder
    public Image(Long imageId, String originalFileName, Long fileSize, String fileName, String endPoint) {
        this.imageId = imageId;
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.endPoint = endPoint;
    }

    public static ImageEntity toEntity(Image image){
        return ImageEntity.builder()
                .imageId(image.getImageId())
                .fileName(image.fileName)
                .originalFileName(image.originalFileName)
                .endPoint(image.endPoint)
                .fileSize(image.fileSize)
                .build();
    }
    public static Image fromEntity(ImageEntity image){
        return Image.builder()
                .imageId(image.getImageId())
                .fileName(image.getFileName())
                .fileSize(image.getFileSize())
                .endPoint(image.getEndPoint())
                .originalFileName(image.getOriginalFileName())
                .build();
    }
}
