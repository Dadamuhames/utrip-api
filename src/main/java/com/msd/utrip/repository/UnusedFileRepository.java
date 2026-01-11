package com.msd.utrip.repository;

import com.msd.utrip.entity.UnusedFileEntity;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UnusedFileRepository extends CrudRepository<UnusedFileEntity, Long> {
  @Query("SELECT f FROM UnusedFileEntity f WHERE f.expireAt <= CURRENT_DATE")
  List<UnusedFileEntity> findExpiredFiles();

  @Transactional
  @Modifying
  @Query("DELETE FROM UnusedFileEntity f WHERE f.expireAt <= CURRENT_DATE")
  void deleteExpiredFiles();

  @Transactional
  @Modifying
  @Query("DELETE FROM UnusedFileEntity f WHERE f.filename = :filename AND f.bucket = :bucket")
  void deleteAllByFileAndBucket(@Param("filename") String filename, @Param("bucket") String bucket);
}
