/*
package com.duoc.plataformaeducativa.controlador;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class S3TestController {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @GetMapping("/s3/test")
    public String testS3() {

        boolean existe =
                amazonS3.doesBucketExistV2(bucketName);

        return existe
                ? "Bucket encontrado"
                : "Bucket NO encontrado";
    }

    @PostMapping("/s3/upload-test")
    public String uploadTest() {

        String contenido = """
                Hola AWS S3

                Esta es una prueba desde Spring Boot.

                Fecha: 2026-05-30
                """;

        byte[] bytes = contenido.getBytes(StandardCharsets.UTF_8);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        metadata.setContentType("text/plain");

        amazonS3.putObject(
                bucketName,
                "prueba/hola.txt",
                new ByteArrayInputStream(bytes),
                metadata
        );

        return "Archivo subido correctamente";
    }
}
*/