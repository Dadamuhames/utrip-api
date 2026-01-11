package com.msd.utrip.scheduler;

import com.msd.utrip.service.file.FileCleanupService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileCleanupScheduler {
  private final FileCleanupService fileCleanupService;

  @Scheduled(cron = "0 0 * * * *")
  public void cleanupUnusedFiles() {
    fileCleanupService.deleteExpiredFiles();
  }
}
