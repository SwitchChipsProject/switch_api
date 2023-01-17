package com.switch_proj.api.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class TokenController {
    private final TokenService tokenService;
}
