package com.pedroribeiro.helpaai.dtos.sector;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorRequestDTO {

    @NotNull(message = "Nome do setor deve ser informado")
    @Size(min=3,max = 100,message = "Nome deve conter entre 3 a 100 caracteres")
    private String name;
}
