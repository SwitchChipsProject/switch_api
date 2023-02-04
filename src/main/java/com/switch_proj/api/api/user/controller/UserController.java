package com.switch_proj.api.api.user.controller;

import com.switch_proj.api.api.auth.dto.Token;
import com.switch_proj.api.api.auth.filter.JwtAuthenticationFilter;
import com.switch_proj.api.api.exception.dto.BadRequestException;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import com.switch_proj.api.api.user.dto.AuthorizeUser;
import com.switch_proj.api.api.user.dto.UserLocation;
import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.service.UserService;
import com.switch_proj.api.api.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) {
        validateParams(user);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/certification/{term-uuid}")
    public ResponseEntity certificationEmail(@PathVariable("term-uuid") String termUuid) {
        userService.certificationEmail(termUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<User>getMyUserWithAuthorities(){
        UserEntity userEntity = userService.getMyUserWithAuthorities();
        User user = User.fromEntity(userEntity);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    private void validateParams(User user) {
        if (user.getUserLocation().getLatitude() == null || user.getUserLocation().getLatitude() == null || user.getUserLocation().getAddress() == null) {
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_MISSING,"위치 정보가 입력되지 않았습니다.");
        }
    }

}
