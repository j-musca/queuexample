package org.musca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import com.rabbitmq.client.Channel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueConsumer.class);

    @RabbitListener(queues = {"words.v1"})
    public void processWords(final Channel channel, final Message message, @Payload final Word word) {
        LOGGER.info("Properties: {}", message.getMessageProperties());
        LOGGER.info("Word: {}", word);

        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            LOGGER.error("Could not ack message! Reason: {}", e.getMessage());
        }
    }
}
