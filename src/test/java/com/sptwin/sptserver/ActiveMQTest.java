package com.sptwin.sptserver;

import com.sptwin.sptserver.activemq.Producer;
import com.sptwin.sptserver.common.Constant;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQTest {

    @Autowired
    private Producer producer;

    /**
     * ActiveMQQueue：【生产者和消费者】
     * 生产/消费模式下，消息发出后会存放在队列中，等待消费者消费；
     */
    @Test
    public void producerAndConsumer() {
        //或 Queue queue = new ActiveMQQueue(Constant.QUEUE_NAME_TEST);
        Destination destination = new ActiveMQQueue(Constant.QUEUE_NAME_TEST);
        for(int i=0; i<10; i++){
            producer.sendMessage(destination, "myname is queue!!!"+i);
        }
    }

    /**
     * ActiveMQTopic:【发布者和订阅者】
     * 订阅者接收topic是需要在topic发布之前订阅,发布者和生产者的代码是一样的，只不过传入的实现类不一样
     */
    @Test
    public void PublisherAndSubscriber() {
        //或 Topic topic = new ActiveMQTopic(Constant.TOPIC_NAME_TEST);
        Destination destination = new ActiveMQTopic(Constant.TOPIC_NAME_TEST);
        for(int i=0; i<10; i++){
            producer.sendMessage(destination, "myname is topic!!!"+i);
        }
    }
}
