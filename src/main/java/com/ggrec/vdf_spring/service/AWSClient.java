package com.ggrec.vdf_spring.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class AWSClient {

    private AmazonS3 s3client;

    @Value("${amazonProperties.region}")
    private String region;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard();
        clientBuilder.setRegion(region);

        if (!accessKey.isEmpty() && !secretKey.isEmpty())
            clientBuilder.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)));
        else
            throw new IllegalArgumentException("No AWS S3 credentials provided in YML file");

        this.s3client = clientBuilder.build();
    }

    public void uploadFileFromURL(URL url, String fileName, String contentType) throws IOException {
        try ( InputStream fileStream = url.openStream();) {
            byte[] bytes = IOUtils.toByteArray(fileStream);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            metadata.setContentLength(bytes.length);

            s3client.putObject(new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(bytes), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }
    }

}
