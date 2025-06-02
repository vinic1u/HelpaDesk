package com.pedroribeiro.helpaai.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "tb_observacao")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT",name = "observacao",nullable = false)
    private String observation;

    @CreationTimestamp
    @Column(name = "criado_em",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_chamado")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private User user;
}
