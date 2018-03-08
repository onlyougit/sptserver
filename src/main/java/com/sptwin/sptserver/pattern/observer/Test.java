package com.sptwin.sptserver.pattern.observer;

public class Test {
    public static void main(String[] args) {
        ProductList productList = ProductList.getInstant();
        JingDongObserver jingDongObserver = new JingDongObserver();
        TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
        productList.addProductObserver(jingDongObserver);
        productList.addProductObserver(taoBaoObserver);
        productList.addProductList("iphone X");
    }
}
