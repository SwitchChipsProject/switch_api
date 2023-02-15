package com.switch_proj.api.api.chip.dto;

import com.switch_proj.api.api.chip.entity.ChipEntity;
import com.switch_proj.api.api.image.dto.Image;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chip {
    private Long chipId;
    private String name;
    private Image image;

    @Builder
    public Chip(Long chipId, String name, Image image) {
        this.chipId = chipId;
        this.name = name;
        this.image = image;
    }

    public static Chip fromEntity(ChipEntity chipEntity) {
        return Chip.builder()
                .chipId(chipEntity.getChipId())
                .name(chipEntity.getName())
                .image(Image.fromEntity(chipEntity.getImageId()))
                .build();
    }
}
