package cn.acshmily.httpredis.controller

import cn.acshmily.httpredis.beans.DemoBean
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource
import java.util.concurrent.TimeUnit

/**
 @Author: Huanghz
 @Description: Redis操作
 @Date:Created in 1:26 下午 2020/5/7
 @ModifyBy:
  * */
@RestController
@RequestMapping('cmd')
class CmdController{
    final static _PREFIX = 'json_'
    /**
     * 根据Key获取值
     * @param key
     * @return
     */
    @GetMapping('{key}')
    DemoBean get(@PathVariable String key){
        def decoder = new JsonSlurper()
        return decoder.parseText(redisTemplate.boundValueOps(_PREFIX+key).get() as String) as DemoBean
    }
    /**
     * 放置
     * @param key
     * @param demoBean
     * @param timeOut
     * @return
     */
    @PostMapping('{key}')
    DemoBean set(@PathVariable String key,@RequestBody DemoBean demoBean,@RequestParam(defaultValue = '5',required = false)String timeOut){

        redisTemplate.boundValueOps(_PREFIX+key).set(JsonOutput.toJson(demoBean),timeOut.toLong(), TimeUnit.MINUTES)
        return demoBean
    }
    @Resource
    private RedisTemplate redisTemplate
}
