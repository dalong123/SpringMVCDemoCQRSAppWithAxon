package com.psamatt.basketdemo.domain;

/**
 * @author psamatt
 */
public class ProductRemovedFromBasket {

    private BasketId basketId;
    private ProductId productId;

    public ProductRemovedFromBasket(BasketId basketId, ProductId productId) {
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
