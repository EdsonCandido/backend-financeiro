package com.zuko.crm.dto.response;

import java.util.List;

public record TransactionListResponseDTO(
        List<TransactionItem> transaction,
        int page,
        int pageSize,
        int totalPages,
        int totalElements
) {
}
