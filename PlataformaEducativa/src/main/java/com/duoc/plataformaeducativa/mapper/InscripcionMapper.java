package com.duoc.plataformaeducativa.mapper;

import java.util.List;

import com.duoc.plataformaeducativa.dto.InscripcionResponseDTO;
import com.duoc.plataformaeducativa.entidad.Inscripcion;

public class InscripcionMapper {
    public static InscripcionResponseDTO toResponseDTO(Inscripcion inscripcion) {

        return new InscripcionResponseDTO(
                inscripcion.getId(),
                inscripcion.getUsuario().getNombre(),
                CursoMapper.toResponseDTOList(inscripcion.getCursos()),
                inscripcion.getTotal(),
                inscripcion.getFechaInscripcion()
        );
    }

    public static List<InscripcionResponseDTO> toResponseDTOList(List<Inscripcion> inscripciones) {

        return inscripciones.stream()
                .map(InscripcionMapper::toResponseDTO)
                .toList();
    }
}
