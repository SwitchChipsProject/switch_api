package com.switch_proj.api.api.auth.controller;

import com.switch_proj.api.api.auth.domain.TokenResponse;
import com.switch_proj.api.api.auth.service.TokenService;
import com.switch_proj.api.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class TokenController {
    private TokenService tokenService;


}
