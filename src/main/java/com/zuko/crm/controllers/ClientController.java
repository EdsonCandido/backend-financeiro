package com.zuko.crm.controllers;

import com.zuko.crm.dto.request.CreateClientRequestDTO;
import com.zuko.crm.entities.ClientEntity;
import com.zuko.crm.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/${id}")
    public ResponseEntity<ClientEntity> findOneById(@PathVariable("id") long id){
        var output = this.clientService.findOne(id);

        return ResponseEntity.ok(output);
    }

    @PostMapping("/")
    public ResponseEntity<ClientEntity> store(@RequestBody CreateClientRequestDTO dto){
        var output = this.clientService.store(dto);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/${id}")
    public ResponseEntity<ClientEntity> changeActive(@PathVariable("id") long id){
        var output = this.clientService.changeActive(id);

        return ResponseEntity.ok(output);
    }
}
