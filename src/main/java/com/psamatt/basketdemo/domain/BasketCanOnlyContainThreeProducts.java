package com.psamatt.basketdemo.domain;

/**
 * @author psamatt
 */
public class BasketCanOnlyContainThreeProducts extends IllegalArgumentException {

    public BasketCanOnlyContainThreeProducts() {
        super("A Basket can only contain three products");
    }
}
