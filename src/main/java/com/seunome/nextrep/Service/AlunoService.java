package com.seunome.nextrep.Service;

import com.seunome.nextrep.Entity.Aluno;
import com.seunome.nextrep.Repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno save(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno update(Long id, Aluno alunoAtualizado) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.setName(alunoAtualizado.getName());
        aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
        return alunoRepository.save(aluno);

    }
    public void deleteById(Long id) {
        if (!alunoRepository.existsById(id)){
            throw new RuntimeException("Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }
}
