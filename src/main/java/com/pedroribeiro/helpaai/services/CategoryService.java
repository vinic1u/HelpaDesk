package com.pedroribeiro.helpaai.services;

import com.pedroribeiro.helpaai.dtos.category.CategoryRequestDTO;
import com.pedroribeiro.helpaai.dtos.category.CategoryResponseDTO;
import com.pedroribeiro.helpaai.entities.Category;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryResponseDTO::new).toList();
    }

    public CategoryResponseDTO findCategoryById(Integer id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de ID: " + id + " não encontrada!"));
        return new CategoryResponseDTO(category);
    }

    public CategoryResponseDTO saveCategory(CategoryRequestDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        categoryRepository.save(category);
        return new CategoryResponseDTO(category);
    }

    public CategoryResponseDTO updateCategory(Integer id,CategoryRequestDTO dto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de ID: " + id + " não encontrada!"));
        category.setName(dto.getName());
        categoryRepository.save(category);
        return new CategoryResponseDTO(category);
    }
}
