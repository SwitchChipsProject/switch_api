package com.switch_proj.api.api.user.mapper;

import com.switch_proj.api.api.user.entity.UserEntity;
import org.apache.catalina.UserDatabase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {
    void saveUser(@Param("user") UserEntity user);
    void updateEmailCertificationState(@Param("userId") Long userId);
    UserEntity findByCertificationCode(@Param("termUuid")String termUuid);

    UserEntity findByUserEmail(@Param("userEmail") String userEmail);
    UserDetails findByUserName(@Param("username")String username);
}
