package com.duoc.plataformaeducativa.servicio;

import java.util.List;

import com.duoc.plataformaeducativa.dto.UsuarioRequestDTO;
import com.duoc.plataformaeducativa.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO crearUsuario(
            UsuarioRequestDTO dto
    );

    List<UsuarioResponseDTO> listarUsuarios();
}
