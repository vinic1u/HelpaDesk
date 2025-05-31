package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.auth.AuthencationLoginDTO;
import com.pedroribeiro.helpaai.dtos.auth.AuthencationRegisterDTO;
import com.pedroribeiro.helpaai.dtos.auth.LoginResponseDTO;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.exceptions.AlreadyRegisteredException;
import com.pedroribeiro.helpaai.infra.TokenService;
import com.pedroribeiro.helpaai.repositories.UserRepository;
import com.pedroribeiro.helpaai.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthencationController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(
            @Valid @RequestBody AuthencationLoginDTO dto){
        LoginResponseDTO token = userService.loginUser(dto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @Valid @RequestBody AuthencationRegisterDTO dto
            ){
        String response = userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
