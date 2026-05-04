package com.seunome.nextrep.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercicios_realizados")
public class ExercicioRealizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;
    @ManyToOne
    @JoinColumn(name = "sessao_de_treino_id", nullable = false)
    private SessaoDeTreino sessaoDeTreino;

    @OneToMany(mappedBy = "exercicioRealizado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Serie> series;


}
