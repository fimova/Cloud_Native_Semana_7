package com.duoc.plataformaeducativa.repositorio.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.duoc.plataformaeducativa.entidad.Asset;
import com.duoc.plataformaeducativa.repositorio.S3Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class S3RepositoryImpl implements S3Repository {

    private final AmazonS3 amazonS3;

    @Override
    public List<Asset> listObjectsInBucket(
            String bucketName
    ) {

        return amazonS3.listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(this::mapS3ObjectToAsset)
                .toList();
    }

    @Override
    public S3ObjectInputStream getObject(
            String bucketName,
            String fileName
    ) throws IOException {

        return amazonS3.getObject(
                bucketName,
                fileName
        ).getObjectContent();
    }

    @Override
    public byte[] downloadFile(
            String bucketName,
            String fileName
    ) throws IOException {

        try (
                S3ObjectInputStream inputStream =
                        getObject(bucketName, fileName)
        ) {

            return inputStream.readAllBytes();
        }
    }

    @Override
    public void moveObject(
            String bucketName,
            String fileKey,
            String destinationFileKey
    ) {

        amazonS3.copyObject(
                bucketName,
                fileKey,
                bucketName,
                destinationFileKey
        );

        amazonS3.deleteObject(
                bucketName,
                fileKey
        );
    }

    @Override
    public void deleteObject(
            String bucketName,
            String fileKey
    ) {

        amazonS3.deleteObject(
                bucketName,
                fileKey
        );
    }

    @Override
    public String uploadFile(
            String bucketName,
            String fileName,
            File file
    ) {

        amazonS3.putObject(
                bucketName,
                fileName,
                file
        );

        return amazonS3.getUrl(
                bucketName,
                fileName
        ).toString();
    }

    private Asset mapS3ObjectToAsset(
            S3ObjectSummary objectSummary
    ) {

        return Asset.builder()
                .name(objectSummary.getKey())
                .key(objectSummary.getKey())
                .url(
                        amazonS3.getUrl(
                                objectSummary.getBucketName(),
                                objectSummary.getKey()
                        )
                )
                .build();
    }

}
