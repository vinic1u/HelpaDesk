package com.pedroribeiro.helpaai.dtos.auth;

import com.pedroribeiro.helpaai.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthencationRegisterDTO {
    private String name;
    private String phone;
    private String email;
    private String password;
    private UserRole role;
}
