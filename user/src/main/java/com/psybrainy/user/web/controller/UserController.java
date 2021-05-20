package com.psybrainy.user.web.controller;

import com.psybrainy.user.domain.dto.UserDtoRequest;
import com.psybrainy.user.domain.dto.UserDtoResponse;
import com.psybrainy.user.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping
    public ResponseEntity<UserDtoResponse> register(UserDtoRequest userRequest){
        return new ResponseEntity<>(userService.save(userRequest), HttpStatus.CREATED);
    }
}
