package com.duoc.plataformaeducativa.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.plataformaeducativa.dto.CursoRequestDTO;
import com.duoc.plataformaeducativa.dto.CursoResponseDTO;
import com.duoc.plataformaeducativa.entidad.Curso;
import com.duoc.plataformaeducativa.exception.DuplicateResourceException;
import com.duoc.plataformaeducativa.mapper.CursoMapper;
import com.duoc.plataformaeducativa.repositorio.CursoRepository;
import com.duoc.plataformaeducativa.servicio.CursoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    public List<CursoResponseDTO> listarCursos() {

        List<Curso> cursos = cursoRepository.findAll();

        return CursoMapper.toResponseDTOList(cursos);
    }

    @Override
    public CursoResponseDTO crearCurso(CursoRequestDTO dto) {

        if (cursoRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException(
                    "Ya existe un curso con ese nombre"
            );
        }

        Curso curso = CursoMapper.toEntity(dto);

        Curso cursoGuardado = cursoRepository.save(curso);

        return CursoMapper.toResponseDTO(cursoGuardado);
    }
}
