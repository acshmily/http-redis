package cn.acshmily.httpredis.beans

import cn.acshmily.httpredis.config.RedisSerializerType

/**
 @Author: Huanghz
 @Description: 测试Bean
 @Date:Created in 1:27 下午 2020/5/7
 @ModifyBy:
  * */
class DemoBean implements RedisSerializerType {
    String type // 开关
    String prd1 // 产品功能1
    String prd2 // 产品功能2
    String prd3 // 产品功能3
    String prd4 // 产品功能4
    String prd5 // 产品功能5
    String prd6 // 产品功能6
}
