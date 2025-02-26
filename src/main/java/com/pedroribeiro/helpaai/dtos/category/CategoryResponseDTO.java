package com.pedroribeiro.helpaai.dtos.category;

import com.pedroribeiro.helpaai.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {

    private Integer id;
    private String name;

    public CategoryResponseDTO(Category entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
