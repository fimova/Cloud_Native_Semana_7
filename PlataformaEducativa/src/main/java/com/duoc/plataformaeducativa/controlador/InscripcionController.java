package com.duoc.plataformaeducativa.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.plataformaeducativa.dto.InscripcionRequestDTO;
import com.duoc.plataformaeducativa.dto.InscripcionResponseDTO;
import com.duoc.plataformaeducativa.servicio.InscripcionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionResponseDTO>> listarInscripciones() {
        return ResponseEntity.ok(
                inscripcionService.listarInscripciones()
        );
    }

    //lista de resumenes de inscripciones
    @GetMapping("/{id}/resumen")
    public ResponseEntity<String> generarResumen(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                inscripcionService.generarResumen(id)
        );
    }

    @PostMapping
    public ResponseEntity<InscripcionResponseDTO> crearInscripcion(@Valid @RequestBody InscripcionRequestDTO dto) {
        InscripcionResponseDTO inscripcionCreada = inscripcionService.crearInscripcion(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inscripcionCreada);
    }
}
