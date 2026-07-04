package com.duoc.consumidor.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.consumidor.entidad.ResumenInscripcion;

public interface ResumenInscripcionRepository extends JpaRepository<ResumenInscripcion, Long> {

    Optional<ResumenInscripcion> findTopByOrderByIdDesc();

}
