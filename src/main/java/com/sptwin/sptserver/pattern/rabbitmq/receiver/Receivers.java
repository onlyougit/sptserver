package com.sptwin.sptserver.pattern.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receivers {

    //----------------------Direct------------------
    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void helloReceiver(String hello) {
        //Thread.sleep(5000);
        //System.out.println(0/0);
        System.out.println("receiver hello: " + hello);
    }

    //----------------------Topic------------------
    @RabbitListener(queues = "order.queue")
    @RabbitHandler
    public void topicReceiver(String hello) {
        System.out.println("receiver topic1: " + hello);
    }
    @RabbitListener(queues = "product.queue")
    @RabbitHandler
    public void topicReceiver2(String hello) {
        System.out.println("receiver topic2: " + hello);
    }

    //----------------------Fanout------------------
    @RabbitListener(queues = "fanout.A")
    @RabbitHandler
    public void fanoutReceiver1(String hello) {
        System.out.println("receiver1 fanoutA: " + hello);
    }
    @RabbitListener(queues = "fanout.B")
    @RabbitHandler
    public void fanoutReceiver2(String hello) {
        System.out.println("receiver2 fanoutB: " + hello);
    }
    @RabbitListener(queues = "fanout.C")
    @RabbitHandler
    public void fanoutReceiver3(String hello) {
        System.out.println("receiver3 fanoutC: " + hello);
    }
}
