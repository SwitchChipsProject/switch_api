package com.switch_proj.api.api.user.mapper;

import com.switch_proj.api.api.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {
    void saveUser(@Param("user") UserEntity user);
    void updateEmailCertificationState(@Param("userId") Long userId);
    UserEntity findByCertificationCode(@Param("termUuid")String termUuid);

    UserDetails findByUserEmail(@Param("userEmail") String userEmail);
}
