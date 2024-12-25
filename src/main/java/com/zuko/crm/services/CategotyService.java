package com.zuko.crm.services;

import com.zuko.crm.dto.request.StoreCategoryRequestDTO;
import com.zuko.crm.entities.CategoryEntity;
import com.zuko.crm.repositorys.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategotyService {
    private final CategoryRepository categoryRepository;


    public CategotyService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> finaAll() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public CategoryEntity findOneById(Long id) {
        if (id == null) throw new RuntimeException("Dados incompletos");
        var exist = categoryRepository.findById(id);
        if (exist.isEmpty()) throw new RuntimeException("Não encontrado");
        return exist.get();
    }
    public CategoryEntity store(StoreCategoryRequestDTO dto){
        if(dto == null) throw new RuntimeException("Dados incompletos");

        CategoryEntity entity = null;
        if(dto.id() == null) {
            entity = new CategoryEntity();
        }else{
            entity = this.findOneById(dto.id());
        }
        entity.setType(dto.type());
        entity.setName(dto.name());

        categoryRepository.save(entity);

        return entity;

    }
}
