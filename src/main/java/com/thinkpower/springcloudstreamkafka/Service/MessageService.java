package com.thinkpower.springcloudstreamkafka.Service;

import com.thinkpower.springcloudstreamkafka.DTO.MessageDTO;
import com.thinkpower.springcloudstreamkafka.Interface.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Resource
    private MyProcessor myProcessor;

    public void sendMessage(MessageDTO dto) {

        Message<MessageDTO> message = MessageBuilder
                .withPayload(dto)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();

        myProcessor.output().send(message);
        logger.info("send message from App-A : {}", message);
    }
}
