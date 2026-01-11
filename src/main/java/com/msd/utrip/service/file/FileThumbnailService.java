package com.msd.utrip.service.file;

import com.msd.utrip.exception.FileUploadException;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.netty.util.internal.StringUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileThumbnailService {
  private final MinioClient minioClient;

  private String getThumbnailPath(
      final String filepath, final Integer width, final Integer height) {
    String bucket = StringUtil.substringBefore(filepath, '/');
    String filename = StringUtil.substringAfter(filepath, '/');

    String extension = StringUtil.substringAfter(filename, '.');
    String fileNameOnly = StringUtil.substringBefore(filename, '.');

    return String.format("%s/%s_%dX%d.%s", bucket, fileNameOnly, width, height, extension);
  }

  public String getThumbnail(final String filepath, final Integer width, final Integer height) {
    String bucket = StringUtil.substringBefore(filepath, '/');
    String filename = StringUtil.substringAfter(filepath, '/');

    try {
      if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
      }

      String thumbFilePath = getThumbnailPath(filepath, width, height);
      String thumbFilename = StringUtil.substringAfter(thumbFilePath, '/');

      if (!isObjectExist(bucket, thumbFilename)) {
        createThumbnail(bucket, filename, thumbFilename, width, height);
      }

      return thumbFilePath;

    } catch (Exception e) {
      throw new FileUploadException(e.getMessage());
    }
  }

  private void createThumbnail(
      final String bucket,
      final String filename,
      final String thumbFilename,
      final Integer width,
      final Integer height)
      throws Exception {
    InputStream stream =
        minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    Thumbnails.of(stream).size(width, height).toOutputStream(outputStream);

    InputStream thumbnailInputStream = new ByteArrayInputStream(outputStream.toByteArray());

    minioClient.putObject(
        PutObjectArgs.builder().bucket(bucket).object(thumbFilename).stream(
                thumbnailInputStream, outputStream.size(), -1)
            .build());
  }

  public boolean isObjectExist(String bucketName, String objectName) {
    try {
      minioClient.statObject(
          StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
      return true;
    } catch (ErrorResponseException e) {
      return false;
    } catch (Exception e) {
      throw new FileUploadException(e.getMessage());
    }
  }
}
