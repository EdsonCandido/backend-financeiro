package com.zuko.crm.dto.request;

public record UpdateUserRequestDTO(
        Long id,
        String username,
        String login,
        String password,
        Boolean isAdmin,
        Boolean isActive
) {
}
