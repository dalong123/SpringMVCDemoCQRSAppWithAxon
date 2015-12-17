package com.psamatt.basketdemo.domain;

/**
 * @author psamatt
 */
public class BasketWasPickedUp {

    private BasketId id;

    public BasketWasPickedUp(BasketId id) {
        this.id = id;
    }

    public BasketId getId() {
        return id;
    }
}
