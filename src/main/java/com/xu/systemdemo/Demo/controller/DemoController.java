package com.xu.systemdemo.Demo.controller;

import com.xu.systemdemo.Demo.dto.Demo;
import com.xu.systemdemo.Demo.service.DemoService;
import com.xu.systemdemo.base.config.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/test.do")
    @ResponseBody
    public Object test(Demo demo){
        List<Demo> demoList = demoService.test(demo);
        redisUtils.set("201900001","徐国强");
        System.out.print("################获取201900001的值:"+redisUtils.get("201900001"));
        return demoList;
    }
}
