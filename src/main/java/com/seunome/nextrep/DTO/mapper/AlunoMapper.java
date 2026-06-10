package com.seunome.nextrep.DTO.mapper;

import com.seunome.nextrep.DTO.request.AlunoRequest;
import com.seunome.nextrep.DTO.response.AlunoResponse;
import com.seunome.nextrep.Entity.Aluno;


public class AlunoMapper {

    public static Aluno toEntity(AlunoRequest request){
        Aluno aluno = new Aluno();
        aluno.setName(request.name());
        aluno.setDataNascimento(request.dataNascimento());
        return aluno;
    }

    public static AlunoResponse toResponse(Aluno aluno){
        return new AlunoResponse(aluno.getId(),
                aluno.getDataInicio(),
                aluno.getName(),
                aluno.getDataNascimento());
    }
}
