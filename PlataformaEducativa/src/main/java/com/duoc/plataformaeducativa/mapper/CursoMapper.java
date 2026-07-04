package com.duoc.plataformaeducativa.mapper;

import java.util.List;

import com.duoc.plataformaeducativa.dto.CursoRequestDTO;
import com.duoc.plataformaeducativa.dto.CursoResponseDTO;
import com.duoc.plataformaeducativa.entidad.Curso;

public class CursoMapper {

    public static Curso toEntity(CursoRequestDTO dto) {

        Curso curso = new Curso();

        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());
        curso.setInstructor(dto.getInstructor());
        curso.setDuracionHoras(dto.getDuracionHoras());
        curso.setCosto(dto.getCosto());

        return curso;
    }

    public static CursoResponseDTO toResponseDTO(Curso curso) {

        return new CursoResponseDTO(
                curso.getId(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getInstructor(),
                curso.getDuracionHoras(),
                curso.getCosto()
        );
    }

    public static List<CursoResponseDTO> toResponseDTOList(List<Curso> cursos) {

        return cursos.stream()
                .map(CursoMapper::toResponseDTO)
                .toList();
    }
}
