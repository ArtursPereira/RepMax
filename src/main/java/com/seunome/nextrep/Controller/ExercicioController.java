package com.seunome.nextrep.Controller;

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
    public ResponseEntity<Exercicio> saveExercicio(@RequestBody Exercicio exercicio) {
         Exercicio exerciciosalvo =  exercicioService.save(exercicio);
         return ResponseEntity.status(HttpStatus.CREATED).body(exerciciosalvo);
    }

    @GetMapping
    public ResponseEntity<List<Exercicio>> getAllExercicio() {
        List<Exercicio> exercicios = exercicioService.findAll();
        return ResponseEntity.ok(exercicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> getExercicioById(@PathVariable Long id) {
        return exercicioService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercicioById(@PathVariable Long id){
        exercicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Exercicio> updateExercicioById(@PathVariable Long id, @RequestBody Exercicio exercicio){
        Exercicio exercicioAtualizado = exercicioService.update(id, exercicio);
        return ResponseEntity.ok(exercicioAtualizado);

    }
}
