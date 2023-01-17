package com.switch_proj.api.api.chip.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChipExchangeEntity {
    private Long chipExchangeId;
    private Long ownedChipId;
    private Long oppositeChipId;
    private Long registerId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String deletedYn;
}
