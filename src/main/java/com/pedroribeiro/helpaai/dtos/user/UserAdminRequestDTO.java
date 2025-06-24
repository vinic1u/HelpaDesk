package com.pedroribeiro.helpaai.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAdminRequestDTO {
    private String name;
    private Integer sectorId;
    private String userRole;
}
