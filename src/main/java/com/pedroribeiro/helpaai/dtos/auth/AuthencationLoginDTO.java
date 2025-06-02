package com.pedroribeiro.helpaai.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthencationLoginDTO {

    @NotNull(message = "Email deve ser informado")
    @Email(message = "Formato de Email invalido")
    private String email;

    @NotNull(message = "Senha deve ser informada")
    private String password;
}
