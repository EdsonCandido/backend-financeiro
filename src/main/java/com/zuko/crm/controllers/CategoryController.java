package com.zuko.crm.controllers;

import com.zuko.crm.dto.request.StoreCategoryRequestDTO;
import com.zuko.crm.entities.CategoryEntity;
import com.zuko.crm.services.CategotyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
    private final CategotyService categotyService;


    public CategoryController(CategotyService categotyService) {
        this.categotyService = categotyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryEntity>> findAll(){
        return ResponseEntity.ok(categotyService.finaAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> findOneById(@PathVariable("id") long id){
        return ResponseEntity.ok(categotyService.findOneById(id));
    }

    @PostMapping("/")
    public ResponseEntity<CategoryEntity> create(@RequestBody StoreCategoryRequestDTO dto){
        return ResponseEntity.ok(categotyService.store(dto));
    }
}
