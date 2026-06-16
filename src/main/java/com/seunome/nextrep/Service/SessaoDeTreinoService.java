package com.seunome.nextrep.Service;

import com.seunome.nextrep.Entity.Exercicio;
import com.seunome.nextrep.Entity.ExercicioRealizado;
import com.seunome.nextrep.Entity.Serie;
import com.seunome.nextrep.Entity.SessaoDeTreino;
import com.seunome.nextrep.Repository.ExercicioRepository;
import com.seunome.nextrep.Repository.SessaoDeTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessaoDeTreinoService {

    private final SessaoDeTreinoRepository sessaoDeTreinoRepository;
    private final ExercicioRepository exercicioRepository;

    public SessaoDeTreino save(SessaoDeTreino sessaoDeTreino) {
        return sessaoDeTreinoRepository.save(sessaoDeTreino);
    }

    public Optional<SessaoDeTreino> findById(Long id){
        return sessaoDeTreinoRepository.findById(id);
    }

    public void deleteById(Long id) {
        if (!sessaoDeTreinoRepository.existsById(id)){
            throw new RuntimeException("Id nao encontrado no BD");
        }
        sessaoDeTreinoRepository.deleteById(id);
    }

    public List<SessaoDeTreino> findAll(){
        return sessaoDeTreinoRepository.findAll();
    }

    public SessaoDeTreino update(Long id, SessaoDeTreino sessaoDeTreino){
        SessaoDeTreino sessaoDeTreinoUpdate = sessaoDeTreinoRepository.findById(id)
                .orElseThrow(()  ->  new RuntimeException("Sessao de Treino not found"));
        sessaoDeTreinoUpdate.setNome(sessaoDeTreino.getNome());
        sessaoDeTreinoUpdate.setData(sessaoDeTreino.getData());
        return sessaoDeTreinoRepository.save(sessaoDeTreinoUpdate);
    }

    public SessaoDeTreino addExercicioRealizado(Long exercicioid, Long id) {
        SessaoDeTreino sessao = sessaoDeTreinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessao de treino nao encontrada"));

        Exercicio exercicio = exercicioRepository.findById(exercicioid)
                .orElseThrow(() -> new RuntimeException("Exercicio nao encontrado"));
        ExercicioRealizado exercicioRealizado = new ExercicioRealizado();
        exercicioRealizado.setExercicio(exercicio);
        exercicioRealizado.setSessaoDeTreino(sessao);
        sessao.getExercicioRealizados().add(exercicioRealizado); // O lombok retorna a lista real n uma copia, entao o add altera diretamente
        return sessaoDeTreinoRepository.save(sessao);
    }

    public void removeExercicioRealizado(Long idSessao, Long idExercicioRealizado) {
        SessaoDeTreino sessaoDeTreino = sessaoDeTreinoRepository.findById(idSessao).
                orElseThrow(() -> new RuntimeException("Sessao de treino nao encontrada"));

        ExercicioRealizado exercicioRealizado = sessaoDeTreino.getExercicioRealizados()
                .stream()
                .filter(er -> er.getId().equals(idExercicioRealizado))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ExercicioRealizado nao encontrado"));

        sessaoDeTreino.getExercicioRealizados().remove(exercicioRealizado); // orphanRemoval deleta o filho quando ele [e removida da lista.
        sessaoDeTreinoRepository.save(sessaoDeTreino);
    }

    public void addSerie(Long idSessao, Long idExercicioRealizado, float carga, int repeticoes) {
        SessaoDeTreino sessaoDeTreino = sessaoDeTreinoRepository.findById(idSessao)
                .orElseThrow(() -> new RuntimeException("Sessao de treino nao encontrada"));

        ExercicioRealizado exercicioRealizado = sessaoDeTreino.getExercicioRealizados()
                .stream()
                .filter(er -> er.getId().equals(idExercicioRealizado))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ExercicioRealizado nao encontrado"));

        Serie serie = new Serie();
        serie.setCarga(carga);
        serie.setReps(repeticoes);
        exercicioRealizado.getSeries().add(serie);

    }
}
