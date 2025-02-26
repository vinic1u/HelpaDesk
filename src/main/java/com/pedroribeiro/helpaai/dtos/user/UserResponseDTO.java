package com.pedroribeiro.helpaai.dtos.user;

import com.pedroribeiro.helpaai.dtos.sector.SectorResponseDTO;
import com.pedroribeiro.helpaai.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private SectorResponseDTO sector;

    public UserResponseDTO(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.sector = new SectorResponseDTO(entity.getSector());
    }
}
