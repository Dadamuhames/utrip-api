package com.msd.utrip.service.file;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
@RequiredArgsConstructor
public class FileGetService {
  private final MinioClient minioClient;

  public Resource getFile(final String bucket, final String filename) {
    try {
      StatObjectResponse stat =
          minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(filename).build());

      InputStream stream =
          minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());

      return new InputStreamResource(stream) {
        @Override
        public String getFilename() {
          return filename;
        }

        @Override
        public long contentLength() {
          return stat.size();
        }
      };

    } catch (Exception e) {
      throw new ResourceAccessException(e.getMessage());
    }
  }

  public String getContentType(String filename) {
    String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    return switch (ext) {
      case "jpg", "jpeg" -> "image/jpeg";
      case "png" -> "image/png";
      case "gif" -> "image/gif";
      case "mp4" -> "video/mp4";
      case "webm" -> "video/webm";
      case "pdf" -> "application/pdf";
      default -> "application/octet-stream";
    };
  }
}
