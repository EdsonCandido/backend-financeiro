package com.zuko.crm.dto.response;

import com.zuko.crm.entities.CategoryEntity;
import com.zuko.crm.entities.TransactionEntity;
import com.zuko.crm.entities.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionItem(
        long accountId,
        CategoryEntity.Type type,
        String description,
        BigDecimal amount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDate transactionDate,
        UserEntity user,
        CategoryEntity category,
        TransactionEntity.StatusTransaction statusTransaction
) {
}
