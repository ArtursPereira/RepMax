package com.seunome.nextrep.DTO.mapper;

import com.seunome.nextrep.DTO.request.ExercicioRequest;
import com.seunome.nextrep.DTO.response.ExercicioResponse;
import com.seunome.nextrep.Entity.Exercicio;

public class ExercicioMapper {


    public static Exercicio toEntity(ExercicioRequest exercicioRequest){
        Exercicio exercicio = new Exercicio();
        exercicio.setName(exercicioRequest.name());
        exercicio.setGrupoMuscular(exercicioRequest.grupoMuscular());
        return exercicio;
    }

    public static ExercicioResponse toResponse(Exercicio exercicio) {
        return new ExercicioResponse(exercicio.getId(),
                exercicio.getName(),
                exercicio.getGrupoMuscular());
    }
}
