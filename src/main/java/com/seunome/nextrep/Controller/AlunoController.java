package com.seunome.nextrep.Controller;

import com.seunome.nextrep.DTO.mapper.AlunoMapper;
import com.seunome.nextrep.DTO.request.AlunoRequest;
import com.seunome.nextrep.DTO.response.AlunoResponse;
import com.seunome.nextrep.Entity.Aluno;
import com.seunome.nextrep.Service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/nextrep/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> saveAluno(@RequestBody AlunoRequest alunoRequest){
        Aluno aluno = AlunoMapper.toEntity(alunoRequest);
        aluno= alunoService.save(aluno);
        AlunoResponse alunoResponse = AlunoMapper.toResponse(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findAlunoById(@PathVariable Long id){
        return alunoService.findById(id)
                .map(AlunoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> findAllAlunos(){
        List<AlunoResponse> alunos = alunoService.findAll()
                .stream()
                .map(AlunoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(alunos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlunoById(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> updateAluno (@PathVariable Long id, @RequestBody AlunoRequest alunoRequest ){
        Aluno aluno = AlunoMapper.toEntity(alunoRequest);
        Aluno alunoAtualizado = alunoService.update(id, aluno);
        AlunoResponse alunoAtualizadoResponse = AlunoMapper.toResponse(alunoAtualizado);
        return ResponseEntity.ok(alunoAtualizadoResponse);

    }
}
