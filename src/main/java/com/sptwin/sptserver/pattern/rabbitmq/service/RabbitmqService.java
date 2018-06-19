package com.sptwin.sptserver.pattern.rabbitmq.service;

public interface RabbitmqService {
    public String testDirect();

    public void testTopic();

    public void testFanout();
}
