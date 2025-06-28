package com.pedroribeiro.helpaai.dtos.category;

import com.pedroribeiro.helpaai.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAdminResponseDTO {
    private Integer id;
    private String name;
    private Boolean deleted;

    public CategoryAdminResponseDTO(Category entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.deleted = entity.getDeleted() != null ? entity.getDeleted() : false ;
    }
}
