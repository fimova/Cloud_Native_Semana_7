package com.duoc.plataformaeducativa.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.plataformaeducativa.dto.CursoRequestDTO;
import com.duoc.plataformaeducativa.dto.CursoResponseDTO;
import com.duoc.plataformaeducativa.servicio.CursoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
        return ResponseEntity.ok(
                cursoService.listarCursos()
        );
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> crearCurso(@Valid @RequestBody CursoRequestDTO dto) {
        CursoResponseDTO cursoCreado = cursoService.crearCurso(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoCreado);
    }
}
