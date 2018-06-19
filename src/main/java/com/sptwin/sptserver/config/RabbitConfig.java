package com.sptwin.sptserver.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;
    //===============Direct Exchange==========
    private static final String DIRECT_QUEUE_NAME = "hello";
    @Bean
    public Queue Queue() {
        return new Queue(DIRECT_QUEUE_NAME);
    }

    //===============Topic Exchange==========
    private static final String ORDER_QUEUE_NAME = "order.queue";
    private static final String PRODUCT_QUEUE_NAME = "product.queue";
    private static final String ORDER_TOPIC_EXCHANGE_NAME = "orderExchange";
    private static final String TOPIC_EXCHANGE_RULE_1 = "order.message";
    private static final String TOPIC_EXCHANGE_RULE_2 = "order.#";
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME);
    }
    @Bean
    public Queue productQueue() {
        return new Queue(PRODUCT_QUEUE_NAME);
    }
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_TOPIC_EXCHANGE_NAME);
    }
    @Bean
    public Binding bindingCoreExchange(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(TOPIC_EXCHANGE_RULE_1);
    }
    @Bean
    public Binding bindingPaymentExchange(Queue productQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(productQueue).to(orderExchange).with(TOPIC_EXCHANGE_RULE_2);
    }

    //===============Fanout Exchange==========
    private static final String FANOUT_QUEUE_A = "fanout.A";
    private static final String FANOUT_QUEUE_B = "fanout.B";
    private static final String FANOUT_QUEUE_C = "fanout.C";
    private static final String FANOUT_EXCHANGE_NAME = "fanoutExchange";
    @Bean
    public Queue AMessage() {
        return new Queue(FANOUT_QUEUE_A);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(FANOUT_QUEUE_B);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(FANOUT_QUEUE_C);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }
    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplatenew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
	/*@Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
	@Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }*/

}
