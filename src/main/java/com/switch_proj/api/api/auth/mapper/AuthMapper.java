package com.switch_proj.api.api.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface AuthMapper {
    void saveRefreshToken(@Param("refreshToken")String refreshToken,@Param("email")String email);
}
