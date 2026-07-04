package com.duoc.plataformaeducativa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 amazonS3() {

        BasicSessionCredentials credentials =
                new BasicSessionCredentials(
                        System.getenv("AWS_ACCESS_KEY_ID"),
                        System.getenv("AWS_SECRET_ACCESS_KEY"),
                        System.getenv("AWS_SESSION_TOKEN")
                );

        return AmazonS3ClientBuilder.standard()
                .withRegion("us-east-1")
                .withCredentials(
                        new AWSStaticCredentialsProvider(credentials)
                )
                .build();
    }
}
