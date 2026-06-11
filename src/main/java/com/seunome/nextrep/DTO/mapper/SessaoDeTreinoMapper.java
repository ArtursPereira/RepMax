package com.seunome.nextrep.DTO.mapper;

import com.seunome.nextrep.DTO.request.SessaoDeTreinoRequest;
import com.seunome.nextrep.DTO.response.SessaoDeTreinoResponse;
import com.seunome.nextrep.Entity.SessaoDeTreino;

public class SessaoDeTreinoMapper {


    public static SessaoDeTreino toEntity(SessaoDeTreinoRequest request) {
        SessaoDeTreino sessaoDeTreino = new SessaoDeTreino();
        sessaoDeTreino.setNome(request.nome());
        sessaoDeTreino.setData(request.data());
        return sessaoDeTreino;
    }

    public static SessaoDeTreinoResponse toResponse(SessaoDeTreino sessaoDeTreino) {
        return new SessaoDeTreinoResponse(sessaoDeTreino.getId(), sessaoDeTreino.getData(), sessaoDeTreino.getNome());
    }
}
