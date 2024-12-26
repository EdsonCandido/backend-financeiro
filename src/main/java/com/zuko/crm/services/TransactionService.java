package com.zuko.crm.services;

import com.zuko.crm.dto.response.TransactionItem;
import com.zuko.crm.dto.response.TransactionListResponseDTO;
import com.zuko.crm.entities.TransactionEntity;
import com.zuko.crm.repositorys.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionListResponseDTO findAll(int page, int pageSize){
        var elements = transactionRepository.findByIsActiveTrue(
                PageRequest.of(page, pageSize, Sort.Direction.DESC, "created_at"))
                .map(el -> new TransactionItem(
                        el.getAccountId(),
                        el.getType(),
                        el.getDescription(),
                        el.getAmount(),
                        el.getCreatedAt(),
                        el.getUpdatedAt(),
                        el.getTransactionDate(),
                        el.getUser(),
                        el.getCategory()
                        ));

        return new TransactionListResponseDTO(
                elements.getContent(), page, pageSize,
                elements.getTotalPages(), (int) elements.getTotalElements());
    }

}
