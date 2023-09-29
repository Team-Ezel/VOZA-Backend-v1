package com.ezel.voza.domain.file.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FileUploadResponse {

    private String awsUrl;
}
