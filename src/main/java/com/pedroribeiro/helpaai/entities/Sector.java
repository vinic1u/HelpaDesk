package com.pedroribeiro.helpaai.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_setor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome",nullable = false,unique = true,length = 150)
    private String name;

    @Column(name = "in_exclusao")
    private Boolean deleted;

    @OneToMany(mappedBy = "sector")
    private List<User> users = new ArrayList<>();
}
