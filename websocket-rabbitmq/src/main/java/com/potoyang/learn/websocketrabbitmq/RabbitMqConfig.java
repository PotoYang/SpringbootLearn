package com.potoyang.learn.websocketrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/8 15:46
 * Modified By:
 * Description:
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue queueTransaction() {
        // true表示持久化该队列
        return new Queue(RabbitConstant.QUEUE_TRANSACTION, true);
    }

    @Bean
    public Queue queueContract() {
        // true表示持久化该队列
        return new Queue(RabbitConstant.QUEUE_CONTRACT, true);
    }

    @Bean
    public Queue queueQualification() {
        // true表示持久化该队列
        return new Queue(RabbitConstant.QUEUE_QUALIFICATION, true);
    }

    /**
     * 声明交互器
     *
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingTransaction() {
        return BindingBuilder
                .bind(queueTransaction())
                .to(directExchange())
                .with(RabbitConstant.RK_TRANSACTION);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingContract() {
        return BindingBuilder
                .bind(queueContract())
                .to(directExchange())
                .with(RabbitConstant.RK_CONTRACT);
    }

    /**
     * 绑定
     *
     * @return
     */
    @Bean
    public Binding bindingQualification() {
        return BindingBuilder
                .bind(queueQualification())
                .to(directExchange())
                .with(RabbitConstant.RK_QUALIFICATION);
    }

}
