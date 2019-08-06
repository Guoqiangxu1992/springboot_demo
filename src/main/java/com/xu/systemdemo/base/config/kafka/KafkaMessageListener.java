package com.xu.systemdemo.base.config.kafka;

import com.xu.systemdemo.common.util.LoggerUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = KafkaTopicConstants.XUGUOQIANG)
    public void handle(String message){
        LoggerUtil.info(KafkaTopicConstants.XUGUOQIANG+"：message------->"+message);
    }


}
