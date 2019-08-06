package com.xu.systemdemo.Demo.thread;

import com.xu.systemdemo.common.util.LoggerUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component

public class Thread {
    @KafkaListener(id = "demo_id" ,topics ="xuguoqiang")
    public void listen(String msgData) {
        LoggerUtil.info("demo receive : "+msgData);
    }
}
