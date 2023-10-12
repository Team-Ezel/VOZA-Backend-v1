package com.ezel.voza.domain.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ezel.voza.domain.file.exception.FileUploadFailedException;
import com.ezel.voza.domain.file.exception.InvalidFormatFileException;
import com.ezel.voza.domain.file.exception.NotAllowedFileException;
import com.ezel.voza.domain.file.service.SingleFileUploadService;
import com.ezel.voza.global.annotation.ServiceWithTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ServiceWithTransactional
@RequiredArgsConstructor
public class SingleFileUploadServiceImpl implements SingleFileUploadService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpg", "image/png", "image/gif");

    @Override
    public String uploadFile(MultipartFile file) {

        String fileName = createFileName(file.getOriginalFilename());

        String contentType = file.getContentType();

        if(!ALLOWED_MIME_TYPES.contains(contentType)) {
            throw new NotAllowedFileException();
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentLength(file.getSize());

        objectMetadata.setContentType(contentType);

        try (InputStream inputStream = file.getInputStream()) {

            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }

        return fileName;
    }

    public void deleteFile(String fileName) {

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String fileName) {

        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {

        try {

            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {

            throw new InvalidFormatFileException();
        }
    }

}
