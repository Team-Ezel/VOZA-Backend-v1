package com.ezel.voza.domain.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface SingleFileUploadService {
    String uploadFile(MultipartFile file);
}
