package com.psamatt.basketdemo.domain;

/**
 * @author psamatt
 */
public class ProductAddedToBasket {

    private BasketId basketId;
    private ProductId productId;
    private String productName;

    public ProductAddedToBasket(BasketId basketId, ProductId productId, String productName) {
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
