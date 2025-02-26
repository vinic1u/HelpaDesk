package com.pedroribeiro.helpaai.dtos.user;

import com.pedroribeiro.helpaai.dtos.sector.SectorRequestDTO;
import com.pedroribeiro.helpaai.entities.Sector;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    private String email;
    private String phone;
    private Integer sectorID;
}
