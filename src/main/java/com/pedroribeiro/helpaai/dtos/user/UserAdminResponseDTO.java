package com.pedroribeiro.helpaai.dtos.user;

import com.pedroribeiro.helpaai.dtos.sector.SectorResponseDTO;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminResponseDTO{

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private SectorResponseDTO sector;
    private UserRole userRole;

    public UserAdminResponseDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.sector = user.getSector() != null ? new SectorResponseDTO(user.getSector()) : null;
        this.userRole = user.getRole();
    }

}
