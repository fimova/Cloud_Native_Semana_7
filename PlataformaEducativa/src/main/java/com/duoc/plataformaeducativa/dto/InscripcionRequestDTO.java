package com.duoc.plataformaeducativa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionRequestDTO {
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotEmpty(message = "Debe seleccionar al menos un curso")
    private List<Long> cursoIds;
}