package com.sptwin.sptserver.pattern.factory.easy;

public class ProductImpl{
    public static IProduct createProduct(String productNo) {
        switch (productNo){
            case "1":return new Product1();
            case "2":return new Product2();
            default:return null;
        }
    }
}
