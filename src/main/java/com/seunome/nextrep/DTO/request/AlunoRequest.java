package com.seunome.nextrep.DTO.request;

import java.time.LocalDate;

public record AlunoRequest(String name, LocalDate dataNascimento) {
}
