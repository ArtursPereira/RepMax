package com.seunome.nextrep.Controller;

import com.seunome.nextrep.Entity.Aluno;
import com.seunome.nextrep.Service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/repmax/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> saveAluno(@RequestBody Aluno aluno){
        Aluno alunoSalvo = alunoService.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    @GetMapping("{/id}")
    public ResponseEntity<Aluno> getAlunoById(Long id){
        return alunoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAlunos(){
        List<Aluno> alunos = alunoService.findAll();
        return ResponseEntity.ok(alunos);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteAlunoById(Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
