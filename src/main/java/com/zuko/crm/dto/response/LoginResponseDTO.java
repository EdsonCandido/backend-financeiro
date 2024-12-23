package com.zuko.crm.dto.response;

import com.zuko.crm.entities.UserEntity;

public record LoginResponseDTO(
        String accessToken,
        long expiresIn,
        UserEntity user
        ) {
}
