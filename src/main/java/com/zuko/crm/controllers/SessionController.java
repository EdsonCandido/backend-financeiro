package com.zuko.crm.controllers;

import com.zuko.crm.dto.request.LoginRequestDTO;
import com.zuko.crm.dto.response.LoginResponseDTO;
import com.zuko.crm.services.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> session(@RequestBody LoginRequestDTO req){
        var result = sessionService.session(req);
        return ResponseEntity.ok(result);
    }
}
