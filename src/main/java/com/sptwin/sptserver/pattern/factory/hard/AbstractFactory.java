package com.sptwin.sptserver.pattern.factory.hard;

import com.sptwin.sptserver.pattern.factory.easy.Shape;

public abstract class AbstractFactory {

    abstract Shape getShape(String shape);

    abstract Color getColor(String color);

}
