package com.zuko.crm.dto.request;

public record CreateClientRequestDTO(
        String corporateReason,
        String fantasyName,
        Long clientId,
        String cpfCnpj
) {
}
