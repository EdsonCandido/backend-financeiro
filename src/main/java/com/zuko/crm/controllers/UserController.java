package com.zuko.crm.controllers;

import com.zuko.crm.dto.request.CreateUserRequestDTO;
import com.zuko.crm.entities.UserEntity;
import com.zuko.crm.repositorys.UserRepository;
import com.zuko.crm.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> create(@RequestBody CreateUserRequestDTO dto){
        var output = this.userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}
