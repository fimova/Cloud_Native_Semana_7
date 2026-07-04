package com.duoc.plataformaeducativa.controlador;

import com.duoc.plataformaeducativa.entidad.Asset;
import com.duoc.plataformaeducativa.servicio.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3ArchivoController {

    private final S3Service s3Service;

    @GetMapping("/listS3Files")
    public List<Asset> listS3Files() {

        return s3Service.getS3Files();
    }

    @GetMapping("/getS3FileContent")
    public String getS3FileContent(
            @RequestParam String fileName
    ) throws IOException {

        return s3Service.getS3FileContent(
                fileName
        );
    }

    @GetMapping("/downloadS3File")
    public ResponseEntity<ByteArrayResource> downloadS3File(
            @RequestParam String fileName
    ) throws IOException {

        byte[] data =
                s3Service.downloadFile(fileName);

        ByteArrayResource resource =
                new ByteArrayResource(data);

        return ResponseEntity.ok()
                .contentLength(data.length)
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\""
                )
                .body(resource);
    }

    @DeleteMapping("/deleteObject")
    public String deleteObject(
            @RequestParam String fileKey
    ) {

        s3Service.deleteObject(fileKey);

        return "Archivo eliminado correctamente";
    }

    @PutMapping("/moveFile")
    public String moveFile(
            @RequestParam String fileKey,
            @RequestParam String destinationFileKey
    ) {

        s3Service.moveObject(
                fileKey,
                destinationFileKey
        );

        return "Archivo movido correctamente";
    }

    @PostMapping(
            value = "/uploadFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadFile(
            @RequestParam MultipartFile file
    ) throws IOException {

        File tempFile =
                Files.createTempFile(
                        "upload-",
                        file.getOriginalFilename()
                ).toFile();

        file.transferTo(tempFile);

        String result =
                s3Service.uploadFile(
                        file.getOriginalFilename(),
                        tempFile
                );

        tempFile.delete();

        return result;
    }
}