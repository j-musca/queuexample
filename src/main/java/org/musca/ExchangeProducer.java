package org.musca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExchangeProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueConsumer.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public ExchangeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 10)
    public void produceHelloWorld() {
        LOGGER.info("Produce message!");

        rabbitTemplate.convertAndSend("input.words.v1", null, new Word("hello"));
    }
}
