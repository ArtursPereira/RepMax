package com.seunome.nextrep.Controller;


import com.seunome.nextrep.DTO.mapper.SessaoDeTreinoMapper;
import com.seunome.nextrep.DTO.request.SessaoDeTreinoRequest;
import com.seunome.nextrep.DTO.response.SessaoDeTreinoResponse;
import com.seunome.nextrep.Entity.SessaoDeTreino;
import com.seunome.nextrep.Service.SessaoDeTreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nextrep/sessaodetreino")
@RequiredArgsConstructor
public class SessaoDeTreinoController {

    private final SessaoDeTreinoService sessaoDeTreinoService;

    @PostMapping
    public ResponseEntity<SessaoDeTreinoResponse> saveSessaoDeTreino(@RequestBody SessaoDeTreinoRequest sessaoDeTreinoRequest) {
        SessaoDeTreino sessaoDeTreino1 = SessaoDeTreinoMapper.toEntity(sessaoDeTreinoRequest);
        sessaoDeTreino1 = sessaoDeTreinoService.save(sessaoDeTreino1);
        SessaoDeTreinoResponse sessaoDeTreinoResponse = SessaoDeTreinoMapper.toResponse(sessaoDeTreino1);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoDeTreinoResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SessaoDeTreinoResponse> findSessaoDeTreinoById(@PathVariable Long id) {
        return sessaoDeTreinoService.findById(id)
                .map(SessaoDeTreinoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<SessaoDeTreinoResponse>> findAllSessoesDeTreino() {
        List<SessaoDeTreinoResponse> sessoesDeTreino = sessaoDeTreinoService.findAll()
                .stream()
                .map(SessaoDeTreinoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(sessoesDeTreino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessaoDeTreinoById(@PathVariable Long id) {
        sessaoDeTreinoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoDeTreinoResponse> updateSessaoDeTreinoById(@PathVariable Long id, @RequestBody SessaoDeTreinoRequest sessaoDeTreinoRequest){
        SessaoDeTreino sessaoDeTreinoAtualizado = SessaoDeTreinoMapper.toEntity(sessaoDeTreinoRequest);
        sessaoDeTreinoAtualizado = sessaoDeTreinoService.update(id, sessaoDeTreinoAtualizado);
        SessaoDeTreinoResponse sessaoDeTreinoResponse = SessaoDeTreinoMapper.toResponse(sessaoDeTreinoAtualizado);
        return ResponseEntity.ok(sessaoDeTreinoResponse);
    }

    @PostMapping("/{sessaoId}/exercicio-realizado/{exercicioRealizadoId}")
    public ResponseEntity<SessaoDeTreinoResponse> adicionarExercicioRealizado(@PathVariable Long sessaoId, @PathVariable Long exercicioRealizadoId){
        SessaoDeTreino sessaoDeTreino =  sessaoDeTreinoService.addExercicioRealizado(sessaoId, exercicioRealizadoId);
        SessaoDeTreinoResponse sessaoDeTreinoResponse = SessaoDeTreinoMapper.toResponse(sessaoDeTreino);
        return  ResponseEntity.ok().body(sessaoDeTreinoResponse);
    }

    @DeleteMapping("/{sessaoId}/exercicio-realizado/{exercicioRealizadoId}")
    public  ResponseEntity<Void> removerExercicioRealizado(@PathVariable Long sessaoId, @PathVariable Long exercicioRealizadoId) {
        sessaoDeTreinoService.removeExercicioRealizado(sessaoId, exercicioRealizadoId);
        return ResponseEntity.noContent().build();
    }


}
