package com.pedroribeiro.helpaai.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_categoria")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "categoria",nullable = false,unique = true,length = 100)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Ticket> tickets = new ArrayList<>();
}
