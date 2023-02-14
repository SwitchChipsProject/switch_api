package com.switch_proj.api.api.chip.entity;

import com.switch_proj.api.api.image.entity.ImageEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChipEntity {
    private Long chipId;
    private String name;
    private String description;
    private ImageEntity imageId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String deletedYn;
}
