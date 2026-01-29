package com.msd.utrip.repository.redis;

import com.msd.utrip.entity.redis.SavedTourEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedTourRepository extends CrudRepository<SavedTourEntity, Long> {

  List<SavedTourEntity> findByUserId(Long userId);
}
