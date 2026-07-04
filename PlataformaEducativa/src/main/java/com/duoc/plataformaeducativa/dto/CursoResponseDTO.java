package com.duoc.plataformaeducativa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponseDTO {
    private Long id;

    private String nombre;

    private String descripcion;

    private String instructor;

    private Integer duracionHoras;

    private Double costo;
}
