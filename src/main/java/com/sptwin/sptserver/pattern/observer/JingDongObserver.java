package com.sptwin.sptserver.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class JingDongObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        String productName = (String)arg;
        System.out.println("京东商品【"+productName+"】已更新");
    }
}
