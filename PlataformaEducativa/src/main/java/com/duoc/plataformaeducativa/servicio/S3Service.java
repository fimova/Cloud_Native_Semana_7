package com.duoc.plataformaeducativa.servicio;

import com.duoc.plataformaeducativa.entidad.Asset;

import java.io.IOException;
import java.util.List;

public interface S3Service {

    String getS3FileContent(
            String fileName
    ) throws IOException;

    List<Asset> getS3Files();

    byte[] downloadFile(
            String fileName
    ) throws IOException;

    void moveObject(
            String fileKey,
            String destinationFileKey
    );

    void deleteObject(
            String fileKey
    );

    String uploadFile(
            String fileName,
            java.io.File file
    );

    String subirArchivoTexto(
             String key,
             String contenido
        ) throws IOException;
}
