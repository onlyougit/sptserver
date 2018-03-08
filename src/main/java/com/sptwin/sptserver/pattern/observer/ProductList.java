package com.sptwin.sptserver.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductList extends Observable{
    private List<String> productList = null;
    private static volatile ProductList singleton;

    private ProductList(){}

    public static ProductList getInstant(){
        if(null == singleton){
            synchronized (ProductList.class){
                if(null == singleton){
                    singleton = new ProductList();
                    singleton.productList = new ArrayList<>();
                }
            }
        }
        return singleton;
    }


    public void addProductObserver(Observer observer){
        this.addObserver(observer);
    }

    public void addProductList(String content){
        System.out.println(content);
        productList.add(content);
        this.setChanged();
        this.notifyObservers(content);
    }
}
