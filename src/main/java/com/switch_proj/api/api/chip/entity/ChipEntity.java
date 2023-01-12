package com.switch_proj.api.api.chip.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChipEntity {
    private Long chipId;
    private String name;
    private Integer state;
    private String description;
    private Long imageId;
    private Long categoryId;
    protected LocalDateTime createdAt;
     protected LocalDateTime deletedAt;
    protected String deletedYn;
}
