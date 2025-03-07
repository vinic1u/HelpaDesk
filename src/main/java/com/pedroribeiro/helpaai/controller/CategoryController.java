package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.category.CategoryRequestDTO;
import com.pedroribeiro.helpaai.dtos.category.CategoryResponseDTO;
import com.pedroribeiro.helpaai.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> findCategoryById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> saveCategory(
            @RequestBody CategoryRequestDTO dto
            ){
        CategoryResponseDTO response = categoryService.saveCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable("id") Integer id,
            @RequestBody CategoryRequestDTO dto
    ){
        CategoryResponseDTO response = categoryService.updateCategory(id,dto);
        return ResponseEntity.ok(response);
    }
}
