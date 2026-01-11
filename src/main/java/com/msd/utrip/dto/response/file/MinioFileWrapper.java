package com.msd.utrip.dto.response.file;

import java.io.InputStream;

public record MinioFileWrapper(InputStream stream, long length, String contentType) {}
