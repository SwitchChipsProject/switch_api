package com.switch_proj.api.api.image.mapper;

import com.switch_proj.api.api.image.entity.ImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {
    void save(@Param("imageEntity")ImageEntity imageEntity);
}
