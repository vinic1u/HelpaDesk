package com.pedroribeiro.helpaai.dtos.auth;

import com.pedroribeiro.helpaai.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthencationRegisterDTO {

    @NotNull(message = "Nome do usuario deve ser informado")
    @NotEmpty(message = "Nome não pode estar em branco")
    private String name;

    @NotNull(message = "Telefone/Ramal deve ser informado")
    @NotEmpty(message = "Telefone/Ramal não pode estar em branco")
    private String phone;

    @NotNull(message = "Email deve ser informado")
    @Email(message = "Formato de Email invalido")
    private String email;

    @NotNull(message = "Senha deve ser informada")
    @NotEmpty(message = "Senha não pode estar em branco")
    private String password;

    // Cuidar disso depois
    private UserRole role;
}
