package com.switch_proj.api.api.user.controller;

import com.switch_proj.api.api.user.mapper.UserMapper;
import com.switch_proj.api.api.user.service.UserService;
import com.switch_proj.api.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<User>signin(@RequestBody User user, HttpServletResponse response){
       userService.login(user,response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> signUp(@Valid @RequestBody User user) {
         userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/certification/{term-uuid}")
    public ResponseEntity<User> certificationEmail( @PathVariable("term-uuid") String termUuid){
        userService.certificationEmail(termUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
