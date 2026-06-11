package com.seunome.nextrep.Controller;

import com.seunome.nextrep.DTO.mapper.ExercicioMapper;
import com.seunome.nextrep.DTO.request.ExercicioRequest;
import com.seunome.nextrep.DTO.response.ExercicioResponse;
import com.seunome.nextrep.Entity.Exercicio;
import com.seunome.nextrep.Service.ExercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/nextrep/exercicios")
@RequiredArgsConstructor
public class ExercicioController {

    private final ExercicioService exercicioService;

    @PostMapping
    public ResponseEntity<ExercicioResponse> saveExercicio(@RequestBody ExercicioRequest exercicioRequest) {
         Exercicio exercicioSalvo = ExercicioMapper.toEntity(exercicioRequest);
         exercicioSalvo = exercicioService.save(exercicioSalvo);
         ExercicioResponse exercicioSalvoResponse = ExercicioMapper.toResponse(exercicioSalvo);
         return ResponseEntity.status(HttpStatus.CREATED).body(exercicioSalvoResponse);
    }

    @GetMapping
    public ResponseEntity<List<ExercicioResponse>> findAllExercicio() {
        List<ExercicioResponse> exercicios = exercicioService.findAll()
                .stream()
                .map(ExercicioMapper::toResponse)
                .toList();
        return ResponseEntity.ok(exercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioResponse> findExercicioById(@PathVariable Long id) {
        return exercicioService.findById(id)
                .map(ExercicioMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercicioById(@PathVariable Long id){
        exercicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ExercicioResponse> updateExercicioById(@PathVariable Long id, @RequestBody ExercicioRequest exercicioRequest){

        Exercicio exercicioAtualizado = ExercicioMapper.toEntity(exercicioRequest);
        exercicioAtualizado = exercicioService.update(id, exercicioAtualizado);
        ExercicioResponse response = ExercicioMapper.toResponse(exercicioAtualizado);
        return ResponseEntity.ok(response);

    }
}
