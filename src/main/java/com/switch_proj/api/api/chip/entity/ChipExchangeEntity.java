package com.switch_proj.api.api.chip.entity;

import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public ChipExchangeEntity(Long chipExchangeId, Long ownedChipId, Long oppositeChipId, Long registerId, LocalDateTime createdAt, LocalDateTime deletedAt, String deletedYn) {
        this.chipExchangeId = chipExchangeId;
        this.ownedChipId = ownedChipId;
        this.oppositeChipId = oppositeChipId;
        this.registerId = registerId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.deletedYn = deletedYn;
    }
}
