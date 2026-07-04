package com.duoc.plataformaeducativa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.plataformaeducativa.entidad.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
