package com.seunome.nextrep.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercicios")
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "grupos_musculares", nullable = false, length = 50)
    private String grupoMuscular;
}
