package com.duoc.plataformaeducativa.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.plataformaeducativa.dto.UsuarioRequestDTO;
import com.duoc.plataformaeducativa.dto.UsuarioResponseDTO;
import com.duoc.plataformaeducativa.entidad.Usuario;
import com.duoc.plataformaeducativa.exception.DuplicateResourceException;
import com.duoc.plataformaeducativa.mapper.UsuarioMapper;
import com.duoc.plataformaeducativa.repositorio.UsuarioRepository;
import com.duoc.plataformaeducativa.servicio.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO crearUsuario(
            UsuarioRequestDTO dto
    ) {

        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {

            throw new DuplicateResourceException(
                    "El correo ya está registrado"
            );
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);

        Usuario usuarioGuardado =
                usuarioRepository.save(usuario);

        return UsuarioMapper.toResponseDTO(
                usuarioGuardado
        );
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {

        List<Usuario> usuarios =
                usuarioRepository.findAll();

        return UsuarioMapper.toResponseDTOList(
                usuarios
        );
    }
}
