package com.pedroribeiro.helpaai.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_usuario")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome",nullable = false,length = 150)
    private String name;

    @Column(name = "email",nullable = false,length = 100,unique = true)
    private String email;

    @Column(name = "telefone",nullable = false,length = 20,unique = true)
    private String phone;



}
