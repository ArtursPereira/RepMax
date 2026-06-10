package com.seunome.nextrep.DTO.response;

import java.time.LocalDate;

public record AlunoResponse(Long id ,LocalDate dataInicio, String name, LocalDate dataNascimento){
}
