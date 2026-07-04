package com.duoc.plataformaeducativa.servicio.impl;

import com.duoc.plataformaeducativa.entidad.Asset;
import com.duoc.plataformaeducativa.repositorio.S3Repository;
import com.duoc.plataformaeducativa.servicio.S3Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Repository s3Repository;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public String getS3FileContent(
            String fileName
    ) throws IOException {

        byte[] fileBytes =
                s3Repository.downloadFile(
                        bucketName,
                        fileName
                );

        return new String(fileBytes);
    }

    @Override
    public List<Asset> getS3Files() {

        return s3Repository.listObjectsInBucket(
                bucketName
        );
    }

    @Override
    public byte[] downloadFile(
            String fileName
    ) throws IOException {

        return s3Repository.downloadFile(
                bucketName,
                fileName
        );
    }

    @Override
    public void moveObject(
            String fileKey,
            String destinationFileKey
    ) {

        s3Repository.moveObject(
                bucketName,
                fileKey,
                destinationFileKey
        );
    }

    @Override
    public void deleteObject(
            String fileKey
    ) {

        s3Repository.deleteObject(
                bucketName,
                fileKey
        );
    }

    @Override
    public String uploadFile(
            String fileName,
            File file
    ) {

        return s3Repository.uploadFile(
                bucketName,
                fileName,
                file
        );
    }

    @Override 
    public String subirArchivoTexto(String key, String contenido ) 
        throws IOException{ 

                File archivo = File.createTempFile( "temp", ".txt" ); 
                
                Files.writeString( archivo.toPath(), contenido );
                
                String url = s3Repository.uploadFile( bucketName, key, archivo );
                
                archivo.delete(); return url;
        }
}
