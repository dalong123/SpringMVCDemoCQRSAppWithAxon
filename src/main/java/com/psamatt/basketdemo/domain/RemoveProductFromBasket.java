package com.psamatt.basketdemo.domain;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author psamatt
 */
public class RemoveProductFromBasket {

    @TargetAggregateIdentifier
    private BasketId basketId;
    private ProductId productId;

    public RemoveProductFromBasket(BasketId basketId, ProductId productId) {
        this.basketId = basketId;
        this.productId = productId;
    }

    public BasketId getBasketId() {
        return basketId;
    }

    public ProductId getProductId() {
        return productId;
    }
}
