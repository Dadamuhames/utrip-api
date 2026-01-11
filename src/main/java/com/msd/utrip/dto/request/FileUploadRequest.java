package com.msd.utrip.dto.request;

import com.msd.utrip.constant.enums.FileBucket;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest(@NotNull MultipartFile file, @NotNull FileBucket bucket) {}
