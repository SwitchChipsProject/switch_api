package com.switch_proj.api.api.auth.controller;

import com.switch_proj.api.api.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class TokenController {
    private TokenService tokenService;


}
