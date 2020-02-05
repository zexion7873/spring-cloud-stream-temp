package com.thinkpower.springcloudstreamkafka.Interface;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {

    String INPUT = "myInput";

    @Input(INPUT)
    SubscribableChannel input();

    String OUTPUT = "myOutput";

    @Output(OUTPUT)
    MessageChannel output();
}
