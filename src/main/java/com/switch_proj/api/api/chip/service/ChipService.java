package com.switch_proj.api.api.chip.service;

import com.switch_proj.api.api.chip.dto.Chip;
import com.switch_proj.api.api.chip.dto.ChipExchange;
import com.switch_proj.api.api.chip.entity.ChipEntity;
import com.switch_proj.api.api.chip.entity.ChipExchangeEntity;
import com.switch_proj.api.api.chip.mapper.ChipMapper;
import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChipService {
    private final ChipMapper chipMapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Chip> findAllChips() {
        List<ChipEntity> chipsList = chipMapper.findAllChips();
        List<Chip> chips = chipsList.stream().map(Chip::fromEntity).collect(Collectors.toList());
        return chips;
    }

    @Transactional
    public void registerChip(ChipExchange chipExchange) {
        UserEntity user = userService.getMyUserWithAuthorities();

        ChipExchange exchange = ChipExchange.builder()
                .ownedChipId(chipExchange.getOwnedChipId())
                .oppositeChipId(chipExchange.getOppositeChipId())
                .registerId(user.getUserId())
                .build();

        ChipExchangeEntity exchangeEntity = exchange.toEntity(exchange);
        chipMapper.saveChip(exchangeEntity);
    }
}
