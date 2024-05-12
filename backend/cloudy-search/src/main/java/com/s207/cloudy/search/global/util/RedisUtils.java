package com.s207.cloudy.search.global.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.s207.cloudy.search.global.error.enums.ErrorCode;
import com.s207.cloudy.search.global.error.exception.RedisException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public boolean extendExpire(String key, Long expiredTime) {
        try {
            redisTemplate.expire(key, expiredTime, TimeUnit.MILLISECONDS);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public <T> boolean saveData(String key, T data, Long expiredTime) {
        try {
            String value = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public <T> Optional<T> getData(String key, Class<T> classType) {
        String value = redisTemplate.opsForValue().get(key);

        if(value == null){
            return Optional.empty();
        }

        try {
            return Optional.of(objectMapper.readValue(value, classType));
        } catch(Exception e){
            throw new RedisException(ErrorCode.JSON_PARSING_ERROR);
        }
    }
}
