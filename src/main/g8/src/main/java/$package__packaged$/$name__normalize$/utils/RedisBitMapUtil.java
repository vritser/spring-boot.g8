package $package$.$name;format="normalize"$.utils;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * RedisBitMapUtil
 */
@Component
public class RedisBitMapUtil {

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
    }

    public Boolean setBit(String key, long offset, boolean flag) {
        return redisTemplate.opsForValue().setBit(key, offset, flag);
    }

    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    public Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>)conn -> conn.stringCommands().bitCount(key.getBytes()));
    }

    public List<Long> bitField(String key, int limit, long offset) {
        return redisTemplate.opsForValue()
            .bitField(key, BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset));
    }

}
