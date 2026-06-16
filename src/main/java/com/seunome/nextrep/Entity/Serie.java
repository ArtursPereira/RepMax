package com.seunome.nextrep.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repeticao", nullable = false)

    private int reps;

    @Column(name = "carga" , nullable = false)
    private float carga;

    @ManyToOne
    @JoinColumn(name = "exercicio_realizado_id", nullable = false)
    private ExercicioRealizado exercicioRealizado;
}
