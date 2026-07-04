package com.duoc.plataformaeducativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionResponseDTO {

    private Long id;

    private String usuario;

    private List<CursoResponseDTO> cursos;

    private Double total;

    private LocalDate fechaInscripcion;
}
