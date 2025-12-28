package com.msd.utrip.repository.redis;

import com.msd.utrip.entity.redis.BotStateEntity;
import org.springframework.data.repository.CrudRepository;

public interface BotStateRepository extends CrudRepository<BotStateEntity, Long> {}
