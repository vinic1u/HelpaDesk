package com.pedroribeiro.helpaai.entities;

import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_chamado")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo",nullable = false,length = 100)
    private String title;

    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @CreationTimestamp
    @Column(name = "criado_em",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "ultima_interacao",nullable = false,updatable = false)
    private LocalDateTime lastInteraction;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Observation> observations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private User client;

    @ManyToOne
    @JoinColumn(name = "id_operador")
    private User operator;
}
