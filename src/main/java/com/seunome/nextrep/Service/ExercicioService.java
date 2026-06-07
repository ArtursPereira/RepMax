package com.seunome.nextrep.Service;

import com.seunome.nextrep.Entity.Exercicio;
import com.seunome.nextrep.Repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public Exercicio save(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public List<Exercicio> findAll() {
        return  exercicioRepository.findAll();
    }

    public Optional<Exercicio> findById(Long id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio update(Long id, Exercicio exercicioAtualizado){
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercicio não encontrado"));
        exercicio.setName(exercicioAtualizado.getName());
        exercicio.setGrupoMuscular(exercicioAtualizado.getGrupoMuscular());
        return exercicioRepository.save(exercicio);

    }

    public void deleteById(Long id) {
        if (!exercicioRepository.existsById(id)){
            throw new RuntimeException("Exercicio não encontrado");
        }
        exercicioRepository.deleteById(id);
    }


}
