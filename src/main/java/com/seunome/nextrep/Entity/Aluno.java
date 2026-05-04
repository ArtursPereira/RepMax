package com.seunome.nextrep.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "data_inicio", nullable = false, updatable = false)
    private LocalDate dataInicio;

    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessaoDeTreino> sessoesDeTreino;



}
