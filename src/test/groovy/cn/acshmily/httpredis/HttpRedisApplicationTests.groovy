package cn.acshmily.httpredis

import cn.acshmily.httpredis.beans.DemoBean
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

import javax.annotation.Resource
import java.util.concurrent.TimeUnit

@SpringBootTest
@Slf4j
class HttpRedisApplicationTests {
    final static _PREFIX = 'json_'

    @Test
    void contextLoads() {
    }
    /**
     * 刷新Redis
     */
    @Test
    void flushRedis(){
        def beans = new DemoBean()
        beans.type = 'test'
        beans.prd2 = 'prd2'
        log.info('开始循环插入Redis')
        for (int i = 0; i < 100000; i++) {
            redisTemplate.boundValueOps(_PREFIX + i).setIfAbsent(beans, 5, TimeUnit.MINUTES)
        }
    }
    @Resource
    private RedisTemplate redisTemplate

}
