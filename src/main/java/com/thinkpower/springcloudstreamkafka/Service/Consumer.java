package com.thinkpower.springcloudstreamkafka.Service;

import com.thinkpower.springcloudstreamkafka.DTO.MessageDTO;
import com.thinkpower.springcloudstreamkafka.Interface.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;

@EnableBinding(MyProcessor.class)
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @StreamListener(MyProcessor.INPUT)
    public void handle(Message<MessageDTO> message) {
        logger.info("App-A Received : {}", message);
        offsetCommit(message);
    }

    @ServiceActivator(inputChannel = "topic2.myGroup.errors")
    public void error(ErrorMessage errorMessage) {
        logger.error("In Custom ErrorHandle");
        logger.error("ErrorMessage : {}", errorMessage);
        logger.error("OriginalMessage : {}", errorMessage.getOriginalMessage());
        logger.error("Error Message : {}", errorMessage.getPayload().getMessage());
    }

    @StreamListener("errorChannel")
    public void errorGlobal(ErrorMessage errorMessage) {
        logger.error("In Global ErrorHandle");
        logger.error("ErrorMessage : {}", errorMessage);
        logger.error("OriginalMessage : {}", errorMessage.getOriginalMessage());
        logger.error("Error Message : {}", errorMessage.getPayload().getMessage());
    }

    /**
     * 手動 Commit Offset
     * @param message 訊息
     */
    private void offsetCommit(Message<?> message) {
        Acknowledgment acknowledgment = message.getHeaders()
                .get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

        logger.info("Acknowledgment : {}", acknowledgment);

        if (acknowledgment != null) {
            acknowledgment.acknowledge();
        }
    }

}

