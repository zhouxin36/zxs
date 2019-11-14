package com.zx.microservice.microservice.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxin
 * @date 2018-12-08
 */
//@RestController
public class KafkaController {

    private final static Logger logger = LoggerFactory.getLogger(KafkaController.class);

    // 为了兼容stream api 使用byte数组
    private final KafkaTemplate<String,byte[]> kafkaTemplate;

    @Value("${message.topic}")
    private String topic;

    @Autowired(required = false)
    public KafkaController(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public String sendMessage(@RequestParam("message") String message){
        return kafkaTemplate.send(topic,0,null,message.getBytes()).toString();
    }

    @KafkaListener(topics = "${message.topic}")
    public void onMessage(String message){
        logger.info("------------>接收消息：{}",message);
    }

}
