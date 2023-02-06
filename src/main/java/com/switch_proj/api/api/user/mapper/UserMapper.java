package com.switch_proj.api.api.user.mapper;

import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.entity.UserLocationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void saveUser(@Param("user") UserEntity user);

    void updateEmailCertificationState(@Param("userId") Long userId);

    void saveUserLocation(@Param("userLocation") UserLocationEntity userLocation);

    boolean existByUserEmail(@Param("email") String email);

    Optional<UserEntity> findByCertificationCode(@Param("tempUuid") String tempUuid);

    Optional<UserEntity> findByUserEmail(@Param("email") String email);

    boolean existByNickname(@Param("nickname") String nickname);
}
