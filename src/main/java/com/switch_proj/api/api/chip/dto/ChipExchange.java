package com.switch_proj.api.api.chip.dto;

import com.switch_proj.api.api.chip.entity.ChipExchangeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChipExchange {
    private Long chipExchangeId;
    @NotNull(message = "자신의 칩을 선택하세요")
    private Long ownedChipId;
    @NotNull(message = "원하는 칩을 선택하세요")
    private Long oppositeChipId;
    private Long registerId;

    @Builder
    public ChipExchange(Long chipExchangeId, Long ownedChipId, Long oppositeChipId, Long registerId) {
        this.chipExchangeId = chipExchangeId;
        this.ownedChipId = ownedChipId;
        this.oppositeChipId = oppositeChipId;
        this.registerId = registerId;
    }

    public static ChipExchangeEntity toEntity(ChipExchange exchange) {
        return ChipExchangeEntity.builder()
                .chipExchangeId(exchange.getChipExchangeId())
                .ownedChipId(exchange.getOwnedChipId())
                .oppositeChipId(exchange.getOppositeChipId())
                .registerId(exchange.getRegisterId())
                .build();
    }
}
