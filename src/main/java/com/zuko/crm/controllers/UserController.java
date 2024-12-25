package com.zuko.crm.controllers;

import com.zuko.crm.dto.request.CreateUserRequestDTO;
import com.zuko.crm.dto.request.UpdateUserRequestDTO;
import com.zuko.crm.entities.UserEntity;
import com.zuko.crm.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> findAll(){
        var output = userService.findAll();
        return ResponseEntity.ok(output);
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> create(@RequestBody CreateUserRequestDTO dto){
        var output = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @PutMapping("/")
    public ResponseEntity<UserEntity> update(@RequestBody UpdateUserRequestDTO dto){
        var output = userService.update(dto);

        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> changeActive(@PathVariable("id") long id){
        var output = userService.changeActive(id);

        return ResponseEntity.ok(output);
    }
}
