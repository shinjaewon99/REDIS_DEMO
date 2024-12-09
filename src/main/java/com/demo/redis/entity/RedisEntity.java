package com.demo.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@RedisHash("people") // key값으로 저장
public class RedisEntity {

    @Id
    private String id; // value 값으로 저장
    private String firstname;
    private String lastname;
}
