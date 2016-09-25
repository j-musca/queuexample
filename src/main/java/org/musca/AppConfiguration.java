package org.musca;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableRabbit
@EnableScheduling
public class AppConfiguration {

    @Bean
    public FanoutExchange inputWords() {
        return new FanoutExchange("input.words.v1", true, false);
    }

    @Bean
    public Queue words() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        return new Queue("words.v1", true, false, false, args);
    }

    @Bean
    public Binding wordBinding(FanoutExchange inputWords, Queue words) {
        return BindingBuilder.bind(words).to(inputWords);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
