package com.xu.systemdemo.base.config.kafka;

import com.alibaba.fastjson.JSON;
import com.xu.systemdemo.common.util.LoggerUtil;
import com.xu.systemdemo.common.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

public class KafkaTemplaeUtil {
    public static void send(String topic, String message) {
        LoggerUtil.info("kafka Producer send message topic="+topic);
        KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) SpringUtil.getBean(KafkaTemplate.class);
        ListenableFuture<SendResult<String, String>> listenableFuture= kafkaTemplate.send(topic, UUID.randomUUID().toString(), message);
        sendCallBack(listenableFuture);
    }
    private static void sendCallBack(ListenableFuture<SendResult<String, String>> listenableFuture){
        boolean result = true;
        try {
            SendResult<String,String> sendResult = listenableFuture.get();
            listenableFuture.addCallback(SuccessCallback -> {
                        LoggerUtil.info("kafka Producer send message success！topic=" + sendResult.getRecordMetadata().topic()+",partition"+sendResult.getRecordMetadata().partition()+",offset="+sendResult.getRecordMetadata().offset());
                        },
                    FailureCallback->LoggerUtil.error("kafka Producer send message error！sendResult=" + JSON.toJSONString(sendResult.getProducerRecord())));
        } catch (Exception e) {
            LoggerUtil.error("Get producer return message error:"+e);
            throw new RuntimeException(e);
        }
    }
}
