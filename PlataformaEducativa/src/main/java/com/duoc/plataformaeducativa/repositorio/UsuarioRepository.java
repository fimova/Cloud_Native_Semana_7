package com.duoc.plataformaeducativa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.plataformaeducativa.entidad.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    boolean existsByCorreo(String correo);
}
