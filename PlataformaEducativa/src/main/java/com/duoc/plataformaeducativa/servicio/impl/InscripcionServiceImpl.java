package com.duoc.plataformaeducativa.servicio.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.plataformaeducativa.dto.InscripcionRequestDTO;
import com.duoc.plataformaeducativa.dto.InscripcionResponseDTO;
import com.duoc.plataformaeducativa.entidad.Curso;
import com.duoc.plataformaeducativa.entidad.Inscripcion;
import com.duoc.plataformaeducativa.entidad.Usuario;
import com.duoc.plataformaeducativa.exception.ResourceNotFoundException;
import com.duoc.plataformaeducativa.mapper.InscripcionMapper;
import com.duoc.plataformaeducativa.repositorio.CursoRepository;
import com.duoc.plataformaeducativa.repositorio.InscripcionRepository;
import com.duoc.plataformaeducativa.repositorio.UsuarioRepository;
import com.duoc.plataformaeducativa.servicio.InscripcionService;
import com.duoc.plataformaeducativa.servicio.RabbitProducer;
import com.duoc.plataformaeducativa.servicio.S3Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    private final UsuarioRepository usuarioRepository;

    private final CursoRepository cursoRepository;

    private final S3Service s3Service;

    private final RabbitProducer rabbitProducer;

    @Override
    @Transactional
    public InscripcionResponseDTO crearInscripcion(
            InscripcionRequestDTO dto
    ) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        List<Curso> cursos = cursoRepository.findAllById(dto.getCursoIds());

        if (cursos.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No se encontraron cursos"
            );
        }

        Double total = cursos.stream()
                .mapToDouble(Curso::getCosto)
                .sum();

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setUsuario(usuario);
        inscripcion.setCursos(cursos);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setTotal(total);

        Inscripcion inscripcionGuardada =
        inscripcionRepository.save(inscripcion);

        String resumen =
                generarResumen(
                        inscripcionGuardada.getId()
                );

        System.out.println("Inscripción guardada correctamente");
        System.out.println("Resumen generado:");
        System.out.println(resumen);

        
        // ---------- RABBIT ----------

        rabbitProducer.enviarResumen(resumen);
        System.out.println("Resumen enviado a Rabbit");

        // ---------- S3 ----------
        try {

        s3Service.subirArchivoTexto(
                inscripcionGuardada.getId()
                        + "/resumen.txt",
                resumen
        );

        System.out.println("Resumen subido a S3");

        } catch (IOException e) {

        throw new RuntimeException(
                "Error subiendo resumen a S3",
                e
        );
        }

        return InscripcionMapper.toResponseDTO(
                inscripcionGuardada
        );
    }

    @Override
    public List<InscripcionResponseDTO> listarInscripciones() {

        List<Inscripcion> inscripciones =
                inscripcionRepository.findAll();

        return InscripcionMapper.toResponseDTOList(
                inscripciones
        );
    }

    @Override
        public String generarResumen(Long inscripcionId) {

        Inscripcion inscripcion =
                inscripcionRepository.findById(inscripcionId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Inscripción no encontrada"
                                )
                        );

        StringBuilder resumen =
                new StringBuilder();

        resumen.append("RESUMEN DE INSCRIPCION\n\n");

        resumen.append("Numero: ")
                .append(inscripcion.getId())
                .append("\n");

        resumen.append("Alumno: ")
                .append(inscripcion.getUsuario().getNombre())
                .append("\n");

        resumen.append("Fecha: ")
                .append(inscripcion.getFechaInscripcion())
                .append("\n\n");

        resumen.append("Cursos:\n");

        for (Curso curso : inscripcion.getCursos()) {

                resumen.append("- ")
                        .append(curso.getNombre())
                        .append("\n");
        }

        resumen.append("\nTotal: $")
                .append(inscripcion.getTotal());

        return resumen.toString();
        }
}
