package com.seunome.nextrep.Controller;


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
    public ResponseEntity<SessaoDeTreino> saveSessaoDeTreino(@RequestBody SessaoDeTreino sessaoDeTreino) {
        SessaoDeTreino sessaoDeTreino1 = sessaoDeTreinoService.save(sessaoDeTreino);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoDeTreino1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SessaoDeTreino> findSessaoDeTreinoById(@PathVariable Long id) {
        return sessaoDeTreinoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<SessaoDeTreino>> findAllSessoesDeTreino() {
        List<SessaoDeTreino> sessoesDeTreino = sessaoDeTreinoService.findAll();
        return ResponseEntity.ok(sessoesDeTreino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessaoDeTreinoById(@PathVariable Long id) {
        sessaoDeTreinoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoDeTreino> updateSessaoDeTreinoById(@PathVariable Long id, @RequestBody SessaoDeTreino sessaoDeTreino){
        SessaoDeTreino sessaoDeTreinoAtualizado = sessaoDeTreinoService.update(id, sessaoDeTreino);
        return ResponseEntity.ok(sessaoDeTreinoAtualizado);
    }


}
