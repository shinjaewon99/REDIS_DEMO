package com.demo.redis;

import com.demo.redis.entity.RedisEntity;
import com.demo.redis.repository.RedisRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    private RedisRepository redisRepository;

    @Test
    @DisplayName("Redis Hash 조회 테스트")
    public void testRedisHash() {
        RedisEntity createMember = RedisEntity.builder().id("1").firstname("shin").lastname("jw").build();
        redisRepository.save(createMember);

        RedisEntity person = redisRepository.findById("1").orElseThrow();

        Assertions.assertThat("shin").isEqualTo(person.getFirstname());
    }
}