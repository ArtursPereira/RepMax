package com.seunome.nextrep.Service;

import com.seunome.nextrep.Entity.SessaoDeTreino;
import com.seunome.nextrep.Repository.SessaoDeTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessaoDeTreinoService {

    private final SessaoDeTreinoRepository sessaoDeTreinoRepository;

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
}
