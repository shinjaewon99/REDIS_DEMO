package com.demo.redis.repository;

import com.demo.redis.entity.RedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RedisEntity, String> {
}
