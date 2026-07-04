package com.duoc.consumidor.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duoc.consumidor.entidad.ResumenInscripcion;
import com.duoc.consumidor.repositorio.ResumenInscripcionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResumenController {

    private final ResumenInscripcionRepository repository;

    @GetMapping("/resumen/ultimo")
    public ResponseEntity<ResumenInscripcion> obtenerUltimoResumen() {

        return repository.findTopByOrderByIdDesc()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());

    }

}
