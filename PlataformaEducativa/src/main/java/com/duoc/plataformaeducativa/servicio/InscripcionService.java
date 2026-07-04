package com.duoc.plataformaeducativa.servicio;

import java.util.List;

import com.duoc.plataformaeducativa.dto.InscripcionRequestDTO;
import com.duoc.plataformaeducativa.dto.InscripcionResponseDTO;

public interface InscripcionService {
    InscripcionResponseDTO crearInscripcion(
            InscripcionRequestDTO dto
    );

    List<InscripcionResponseDTO> listarInscripciones();

    String generarResumen(Long inscripcionId);
}
