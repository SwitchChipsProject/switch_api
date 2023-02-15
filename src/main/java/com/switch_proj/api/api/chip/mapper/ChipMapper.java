package com.switch_proj.api.api.chip.mapper;

import com.switch_proj.api.api.chip.dto.Chip;
import com.switch_proj.api.api.chip.entity.ChipEntity;
import com.switch_proj.api.api.chip.entity.ChipExchangeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChipMapper {
    List<ChipEntity> findAllChips();
    void saveChip(@Param("exchangeEntity")ChipExchangeEntity chipExchangeEntity);
}
