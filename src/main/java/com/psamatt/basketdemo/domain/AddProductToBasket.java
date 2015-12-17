package com.psamatt.basketdemo.domain;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author psamatt
 */
public class AddProductToBasket {

    @TargetAggregateIdentifier
    private BasketId basketId;
    private ProductId productId;
    private String productName;

    public AddProductToBasket(BasketId basketId, ProductId productId, String productName) {
        this.basketId = basketId;
        this.productId = productId;
        this.productName = productName;
    }

    public BasketId getBasketId() {
        return basketId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
