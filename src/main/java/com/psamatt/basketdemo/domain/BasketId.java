package com.psamatt.basketdemo.domain;

import java.util.UUID;

/**
 * @author psamatt
 */
public class BasketId {

    private UUID id;

    private BasketId(UUID id) {
        this.id = id;
    }

    /**
     * Generate
     *
     * @return BasketId
     */
    public static BasketId generate() {
        return new BasketId(UUID.randomUUID());
    }

    /**
     * From string
     *
     * @param basketId
     * @return BasketId
     */
    public static BasketId fromString(String basketId) {
        return new BasketId(UUID.fromString(basketId));
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
