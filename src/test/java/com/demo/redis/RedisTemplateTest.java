package com.demo.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    @DisplayName("Null 값 테스트")
    void redisTemplateNull() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "nonKey";
        String value = valueOperations.get(key);
        Assertions.assertNull(value);
    }

    @Test
    @DisplayName("데이터 덮어쓰기 테스트")
    void redisTemplateOverwrite() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "name";
        valueOperations.set(key, "shin");

        // 값을 덮어쓰는 테스트
        valueOperations.set(key, "kim");
        String value = valueOperations.get(key);
        Assertions.assertEquals(value, "kim"); // 덮어쓴 값이 반환되어야 함
    }

    @Test
    @DisplayName("set, get 테스트")
    void redisTemplateString() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "name";
        valueOperations.set(key, "shin");
        String value = valueOperations.get(key);
        Assertions.assertEquals(value, "shin");
    }

    @Test
    @DisplayName("만료시간 테스트")
    void redisTemplateExpire() throws InterruptedException {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "tempName";
        valueOperations.set(key, "tempValue");
        redisTemplate.expire(key, 1, TimeUnit.SECONDS); // 1초 후에 만료되도록 설정

        Thread.sleep(1500); // 1.5초 대기
        Object value = valueOperations.get(key);
        Assertions.assertNull(value); // 만료된 값은 null이어야 함
    }

    @Test
    @DisplayName("데이터 일관성 테스트")
    void redisTemplateHash() {
        String key = "user:1000";
        Map<String, String> user = new HashMap<>();
        user.put("name", "shin");
        user.put("type", "person");

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, user);

        String value = hashOperations.get(key, "name");
        Assertions.assertEquals("shin", value);
    }
}

