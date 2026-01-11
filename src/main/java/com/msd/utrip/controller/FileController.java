package com.msd.utrip.controller;

import com.msd.utrip.dto.request.FileUploadRequest;
import com.msd.utrip.dto.response.file.FileUploadResponse;
import com.msd.utrip.service.file.FileGetService;
import com.msd.utrip.service.file.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
  private final FileUploadService fileUploadService;
  private final FileGetService fileGetService;

  @GetMapping("/{bucket}/{filename}")
  public ResponseEntity<Resource> getFile(
      @PathVariable String bucket, @PathVariable String filename) {

    Resource resource = fileGetService.getFile(bucket, filename);

    String contentType = fileGetService.getContentType(filename);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
        .header(HttpHeaders.ACCEPT_RANGES, "bytes")
        .body(resource);
  }

  @PostMapping
  public ResponseEntity<FileUploadResponse> upload(@Valid final FileUploadRequest request) {

    FileUploadResponse response = fileUploadService.upload(request.bucket(), request.file());

    return ResponseEntity.ok(response);
  }
}
