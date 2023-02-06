package com.switch_proj.api.api.user.controller;

import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.service.UserService;
import com.switch_proj.api.api.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/certification/{temp-uuid}")
    public ResponseEntity certificationEmail(@PathVariable("temp-uuid") String tempUuid) {
        userService.certificationEmail(tempUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<User> getMyUserWithAuthorities() {
        UserEntity userEntity = userService.getMyUserWithAuthorities();
        User user = User.fromEntity(userEntity);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
