package com.duoc.plataformaeducativa.servicio;

import java.util.List;

import com.duoc.plataformaeducativa.dto.CursoRequestDTO;
import com.duoc.plataformaeducativa.dto.CursoResponseDTO;

public interface CursoService {

    List<CursoResponseDTO> listarCursos();

    CursoResponseDTO crearCurso(CursoRequestDTO dto);
}
