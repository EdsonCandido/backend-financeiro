package com.zuko.crm.controllers;

import com.zuko.crm.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public ResponseEntity<> findAll(){

    }

    public ResponseEntity<> findOne(){

    }

    public ResponseEntity<> stode(@RequestBody ){

    }

}
