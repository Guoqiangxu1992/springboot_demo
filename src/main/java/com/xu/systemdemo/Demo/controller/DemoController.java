package com.xu.systemdemo.Demo.controller;

import com.alibaba.fastjson.JSON;
import com.xu.systemdemo.Demo.dto.Demo;
import com.xu.systemdemo.Demo.service.DemoService;
import com.xu.systemdemo.base.config.kafka.KafkaTemplaeUtil;
import com.xu.systemdemo.base.config.redis.RedisUtils;
import com.xu.systemdemo.common.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/common")
public class DemoController {
    @Autowired
    private DemoService demoService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/test.do")
    @ResponseBody
    public Object test(Demo demo){
        KafkaTemplaeUtil.send(demo.getTopic(),demo.getMessage());
        List<Demo> demoList = demoService.test(demo);
        LoggerUtil.info("demoList:"+ JSON.toJSONString(demoList));
        LoggerUtil.debug("demoList:"+ JSON.toJSONString(demoList));
        redisUtils.set("201900001","徐国强");
        LoggerUtil.info("################获取201900001的值:"+redisUtils.get("201900001"));
        return demoList;
    }
}
