package com.msd.utrip.service.file;

import com.msd.utrip.entity.UnusedFileEntity;
import com.msd.utrip.repository.UnusedFileRepository;
import io.minio.MinioClient;
import io.minio.RemoveObjectsArgs;
import io.minio.messages.DeleteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileCleanupService {
  private final MinioClient minioClient;
  private final UnusedFileRepository unusedFileRepository;

  @Transactional
  public void saveFile(final String bucket, final String filename) {
    UnusedFileEntity file =
        UnusedFileEntity.builder()
            .bucket(bucket)
            .filename(filename)
            .expireAt(LocalDateTime.now().plusDays(2))
            .build();

    unusedFileRepository.save(file);
  }

  public void deleteByFileAndBucket(final String filepath) {
    String bucket = StringUtil.substringBefore(filepath, '/');
    String filename = StringUtil.substringAfter(filepath, '/');

    unusedFileRepository.deleteAllByFileAndBucket(filename, bucket);
  }

  @Transactional
  public void deleteExpiredFiles() {
    List<UnusedFileEntity> files = unusedFileRepository.findExpiredFiles();

    Map<String, List<DeleteObject>> deleteMap = new HashMap<>();

    for (var file : files) {
      String bucket = file.getBucket();
      String filename = file.getFilename();

      deleteMap.computeIfAbsent(bucket, k -> new ArrayList<>()).add(new DeleteObject(filename));
    }

    for (var entry : deleteMap.entrySet()) {
      minioClient.removeObjects(
          RemoveObjectsArgs.builder().bucket(entry.getKey()).objects(entry.getValue()).build());
    }

    unusedFileRepository.deleteExpiredFiles();
  }
}
