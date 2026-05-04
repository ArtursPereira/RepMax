package com.seunome.nextrep.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sessoes_de_treino")
public class SessaoDeTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @OneToMany(mappedBy = "sessaoDeTreino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExercicioRealizado> exercicioRealizados;
}
