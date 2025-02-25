package com.pedroribeiro.helpaai.dtos.sector;

import com.pedroribeiro.helpaai.entities.Sector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorResponseDTO {

    private Integer id;
    private String name;

    public  SectorResponseDTO(Sector entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
