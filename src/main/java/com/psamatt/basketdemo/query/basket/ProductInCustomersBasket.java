package com.psamatt.basketdemo.query.basket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author psamatt
 */
public class ProductInCustomersBasket {

    public final String productId;
    public final String productName;

    @JsonCreator
    public ProductInCustomersBasket(@JsonProperty("productId") String productId, @JsonProperty("productName") String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
