package com.pedroribeiro.helpaai.dtos.sector;

import com.pedroribeiro.helpaai.entities.Sector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorAdminResponseDTO {

        private Integer id;
        private String name;
        private Boolean deleted;

        public  SectorAdminResponseDTO(Sector entity){
            this.id = entity.getId();
            this.name = entity.getName();
            this.deleted = entity.getDeleted() != null && entity.getDeleted();
        }
}
