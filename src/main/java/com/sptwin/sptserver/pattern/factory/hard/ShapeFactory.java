package com.sptwin.sptserver.pattern.factory.hard;

import com.sptwin.sptserver.pattern.factory.easy.Circle;
import com.sptwin.sptserver.pattern.factory.easy.Rectangle;
import com.sptwin.sptserver.pattern.factory.easy.Shape;
import com.sptwin.sptserver.pattern.factory.easy.Square;

public class ShapeFactory extends AbstractFactory{
    public Shape getShape(String shapeType){
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }
        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    Color getColor(String color) {
        return null;
    }
}
