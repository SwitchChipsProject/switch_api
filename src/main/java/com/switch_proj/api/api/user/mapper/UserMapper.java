package com.switch_proj.api.api.user.mapper;

import com.switch_proj.api.api.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {
    void saveUser(@Param("user") UserEntity user);

    UserDetails findByUserEmail(@Param("userEmail") String userEmail);
}
