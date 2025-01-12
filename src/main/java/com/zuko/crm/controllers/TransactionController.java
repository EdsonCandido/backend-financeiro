package com.zuko.crm.controllers;

import com.zuko.crm.dto.request.StoreTransactionRequestDTO;
import com.zuko.crm.dto.response.TransactionListResponseDTO;
import com.zuko.crm.entities.TransactionEntity;
import com.zuko.crm.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public ResponseEntity<TransactionListResponseDTO> findAll() {
        var output = this.transactionService.findAll();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/${id}")
    public ResponseEntity<TransactionEntity> findOne(@PathVariable("id") long id) {
        var output = this.transactionService.findOneById(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/")
    public ResponseEntity<TransactionEntity> store(@RequestBody StoreTransactionRequestDTO dto, JwtAuthenticationToken token) {
        var output = this.transactionService.store(dto, token);
        return ResponseEntity.ok(output);
    }

}
