package com.ezel.voza.domain.file.presentation;

import com.ezel.voza.domain.file.presentation.response.FileUploadResponse;
import com.ezel.voza.domain.file.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> upload(@RequestPart(value = "file", required = false) MultipartFile file) {
        FileUploadResponse fileUploadResponse = fileUploadService.execute(file);
        return new ResponseEntity<>(fileUploadResponse, HttpStatus.CREATED);
    }
}
