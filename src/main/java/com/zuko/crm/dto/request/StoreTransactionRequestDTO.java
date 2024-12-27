package com.zuko.crm.dto.request;

import com.zuko.crm.entities.CategoryEntity;
import com.zuko.crm.entities.TransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StoreTransactionRequestDTO(
        Long accountId,
        String description,
        CategoryEntity.Type type,
        BigDecimal amount,
        LocalDate transactionDate,
        Long categoryId,
        TransactionEntity.StatusTransaction statusTransaction
) {
}
