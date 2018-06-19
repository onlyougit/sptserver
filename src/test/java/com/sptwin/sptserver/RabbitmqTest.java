package com.sptwin.sptserver;

import com.sptwin.sptserver.pattern.rabbitmq.service.RabbitmqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {
    @Autowired
    private RabbitmqService rabbitmqService;

    @Test
    public void testDirect(){
        String response = rabbitmqService.testDirect();
        System.out.println(response);
    }
    @Test
    public void testTopic(){
        rabbitmqService.testTopic();
    }
    @Test
    public void testFanout(){
        rabbitmqService.testFanout();
    }
}
