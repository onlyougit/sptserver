package com.sptwin.sptserver.pattern.factory.hard;

import com.sptwin.sptserver.pattern.factory.easy.Shape;

public class ColorFactory  extends AbstractFactory{
    @Override
    Shape getShape(String shape) {
        return null;
    }

    public Color getColor(String color){
        if(color.equalsIgnoreCase("")){
            return new Red();
        }
        if(color.equalsIgnoreCase("")){
            return new Green();
        }
        if(color.equalsIgnoreCase("")){
            return new Blue();
        }
        return null;
    }
}
