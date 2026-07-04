package com.duoc.plataformaeducativa.dto;

import com.duoc.plataformaeducativa.entidad.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;

    private String nombre;

    private String correo;

    private Rol rol;
}
