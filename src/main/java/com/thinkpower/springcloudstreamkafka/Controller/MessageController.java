package com.thinkpower.springcloudstreamkafka.Controller;

import com.thinkpower.springcloudstreamkafka.DTO.MessageDTO;
import com.thinkpower.springcloudstreamkafka.Interface.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Resource
    private MyProcessor myProcessor;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void publishMessage(@RequestBody MessageDTO payload) {

        logger.info("sendMessage API : {}", payload);

        Message<MessageDTO> message = MessageBuilder
                .withPayload(payload)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        logger.info("App-A send message : {}", message.getPayload());
        myProcessor.output().send(message);
    }
}

