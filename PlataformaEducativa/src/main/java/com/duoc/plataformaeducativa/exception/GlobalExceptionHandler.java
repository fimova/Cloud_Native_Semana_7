package com.duoc.plataformaeducativa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.duoc.plataformaeducativa.dto.ErrorResponseDTO;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

private static final Logger log =
        LoggerFactory.getLogger(
                GlobalExceptionHandler.class
        );

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponseDTO> manejarNotFound(
        ResourceNotFoundException ex
) {

    ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.name(),
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(error);
}

@ExceptionHandler(DuplicateResourceException.class)
public ResponseEntity<ErrorResponseDTO> manejarDuplicado(
        DuplicateResourceException ex
) {

    ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.name(),
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(error);
}

@ExceptionHandler(BadRequestException.class)
public ResponseEntity<ErrorResponseDTO> manejarBadRequest(
        BadRequestException ex
) {

    ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.name(),
            ex.getMessage()
    );

    return ResponseEntity.badRequest()
            .body(error);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> manejarValidaciones(
        MethodArgumentNotValidException ex
) {

    Map<String, String> errores = new HashMap<>();

    ex.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                    errores.put(
                            error.getField(),
                            error.getDefaultMessage()
                    )
            );

    return ResponseEntity.badRequest()
            .body(errores);
}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponseDTO> manejarGeneral(
        Exception ex
) {

    log.error(
            "Error interno",
            ex
    );

    ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.name(),
            "Error interno"
    );

    return ResponseEntity.status(
            HttpStatus.INTERNAL_SERVER_ERROR
    ).body(error);
}

}
