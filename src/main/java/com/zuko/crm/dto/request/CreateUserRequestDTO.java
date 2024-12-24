package com.zuko.crm.dto.request;

public record CreateUserRequestDTO(
        String username,
        String login,
        String password,
        Boolean isAdmin,
        Boolean isActive
) {
}
