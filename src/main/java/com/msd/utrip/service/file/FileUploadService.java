package com.msd.utrip.service.file;

import com.msd.utrip.constant.enums.FileBucket;
import com.msd.utrip.dto.response.file.FileUploadResponse;
import com.msd.utrip.exception.FileUploadException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {
  private final MinioClient minioClient;
  private final FileCleanupService cleanupService;

  private String getNewFileName(final String fileName) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    String ext = StringUtils.substringAfterLast(fileName, ".");

    return String.format("%d.%s", timestamp.getTime(), ext);
  }

  public FileUploadResponse upload(final FileBucket bucket, final MultipartFile file) {
    try {
      if (!minioClient.bucketExists(
          BucketExistsArgs.builder().bucket(bucket.getBucket()).build())) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket.getBucket()).build());
      }

      String fileName = getNewFileName(file.getOriginalFilename());

      String contentType = file.getContentType();

      minioClient.putObject(
          PutObjectArgs.builder()
              .bucket(bucket.getBucket())
              .contentType(contentType)
              .object(fileName)
              .stream(file.getInputStream(), file.getSize(), -1)
              .build());

      cleanupService.saveFile(bucket.getBucket(), fileName);

      String filepath = String.format("%s/%s", bucket.getBucket(), fileName);

      return new FileUploadResponse(filepath);

    } catch (Exception e) {
      throw new FileUploadException(e.getMessage());
    }
  }
}
