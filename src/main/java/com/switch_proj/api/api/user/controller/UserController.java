package com.switch_proj.api.api.user.controller;

import com.switch_proj.api.api.auth.dto.Token;
import com.switch_proj.api.api.auth.filter.JwtAuthenticationFilter;
import com.switch_proj.api.api.user.dto.AuthorizeUser;
import com.switch_proj.api.api.user.entity.UserEntity;
import com.switch_proj.api.api.user.service.UserService;
import com.switch_proj.api.api.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/certification/{term-uuid}")
    public ResponseEntity certificationEmail(@PathVariable("term-uuid") String termUuid) {
        userService.certificationEmail(termUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
