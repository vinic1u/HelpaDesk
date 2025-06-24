package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.user.UserAdminRequestDTO;
import com.pedroribeiro.helpaai.dtos.user.UserAdminResponseDTO;
import com.pedroribeiro.helpaai.dtos.user.UserResponseDTO;
import com.pedroribeiro.helpaai.dtos.user.UserUpdateRequestDTO;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.exceptions.NotAllowedException;
import com.pedroribeiro.helpaai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserAdminResponseDTO>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(
            @PathVariable Integer  id
    ){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(
            @PathVariable Integer id,
            @RequestBody UserAdminRequestDTO dto
    ){
        String response = userService.updateUser(id,dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyUserData(
            @AuthenticationPrincipal User user
            ){
        return ResponseEntity.ok(userService.getMyUserData(user));
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateMyUserData(
            @AuthenticationPrincipal User user ,
            @RequestBody UserUpdateRequestDTO dto
            ){
        String response = userService.updateMyUserData(user,dto);
        return ResponseEntity.ok(response);
    }
}
