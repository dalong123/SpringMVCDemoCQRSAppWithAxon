package com.psamatt.basketdemo.domain;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author psamatt
 */
public class PickUpBasket {

    @TargetAggregateIdentifier
    private BasketId id;

    public PickUpBasket(BasketId id) {
        this.id = id;
    }

    public BasketId getId() {
        return id;
    }
}
