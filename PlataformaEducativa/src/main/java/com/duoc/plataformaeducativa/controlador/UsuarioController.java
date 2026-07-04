package com.duoc.plataformaeducativa.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.plataformaeducativa.dto.UsuarioRequestDTO;
import com.duoc.plataformaeducativa.dto.UsuarioResponseDTO;
import com.duoc.plataformaeducativa.servicio.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(
                usuarioService.listarUsuarios()
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario( @Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioCreado = usuarioService.crearUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioCreado);
    }
}
