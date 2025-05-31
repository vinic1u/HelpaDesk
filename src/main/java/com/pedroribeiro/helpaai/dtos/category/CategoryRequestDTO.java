package com.pedroribeiro.helpaai.dtos.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotNull(message = "Categoria deve ser Informada")
    @NotEmpty(message = "Categoria não pode estar em branco")
    private String name;
}
