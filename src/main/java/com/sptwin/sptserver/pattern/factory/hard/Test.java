package com.sptwin.sptserver.pattern.factory.hard;

import com.sptwin.sptserver.pattern.factory.easy.Shape;

public class Test {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("SHAPE");
        Shape shape = abstractFactory.getShape("CIRCLE");
        shape.draw();
    }
}
