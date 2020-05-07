package cn.acshmily.httpredis.config


import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * Redis序列化配置
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration)
class RedisConfig {
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class)
        ObjectMapper mapper = new ObjectMapper()
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfSubType(RedisSerializerType).build()
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL)
        serializer.setObjectMapper(mapper)
        redisTemplate.setValueSerializer(serializer)
        redisTemplate.setKeySerializer(new StringRedisSerializer())
        redisTemplate.setHashKeySerializer(new StringRedisSerializer())
        redisTemplate.setHashValueSerializer(serializer)
        redisTemplate.afterPropertiesSet()
        redisTemplate
    }
}
