package com.sptwin.sptserver.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class TaoBaoObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        String productName = (String)arg;
        System.out.println("淘宝商品【"+productName+"】已更新");
    }
}
