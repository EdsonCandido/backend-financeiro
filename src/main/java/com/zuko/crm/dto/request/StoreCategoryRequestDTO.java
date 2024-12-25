package com.zuko.crm.dto.request;

import com.zuko.crm.entities.CategoryEntity;

public record StoreCategoryRequestDTO(
        Long id,
        String name,
        CategoryEntity.Type type
) {
}
