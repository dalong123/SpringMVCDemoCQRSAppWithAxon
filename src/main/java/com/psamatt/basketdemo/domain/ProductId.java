package com.psamatt.basketdemo.domain;

import java.util.UUID;

/**
 * @author psamatt
 */
public class ProductId {

    private UUID id;

    public ProductId(UUID id) {
        this.id = id;
    }

    /**
     * From String
     *
     * @param productId
     * @return ProductId
     */
    public static ProductId fromString(String productId) {
        return new ProductId(UUID.fromString(productId));
    }

    @Override
    public boolean equals(Object obj) {
        ProductId other = (ProductId)obj;

        return 0 == id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
