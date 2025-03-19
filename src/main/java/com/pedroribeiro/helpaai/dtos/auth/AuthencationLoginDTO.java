package com.pedroribeiro.helpaai.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthencationLoginDTO {

    private String email;
    private String password;
}
