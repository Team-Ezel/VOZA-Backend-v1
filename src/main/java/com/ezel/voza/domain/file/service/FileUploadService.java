package com.ezel.voza.domain.file.service;

import com.ezel.voza.domain.file.presentation.response.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileUploadResponse execute(MultipartFile file);
}
