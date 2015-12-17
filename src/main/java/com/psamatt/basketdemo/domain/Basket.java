package com.psamatt.basketdemo.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;

/**
 * @author psamatt
 */
public class Basket extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private BasketId id;
    private ArrayList<ProductId> products = new ArrayList<>();

    @SuppressWarnings("UnusedDeclaration")
    Basket() {}

    public Basket(BasketId id) {
        this.id = id;

        apply(new BasketWasPickedUp(id));
    }

    public static Basket pickUp(BasketId id) {
        return new Basket(id);
    }

    /**
     * Add product to basket
     *
     * @param productId
     * @param productName
     */
    public void addProduct(ProductId productId, String productName) {

        if (!this.products.contains(productId)) {

            if (this.products.size() >= 3) {
                throw new BasketCanOnlyContainThreeProducts();
            }

            apply(new ProductAddedToBasket(this.id, productId, productName));
        }
    }

    /**
     * Remove product from basket
     *
     * @param productId
     */
    public void removeProduct(ProductId productId) {
        if (this.products.contains(productId)) {
            apply(new ProductRemovedFromBasket(this.id, productId));
        }
    }

    @EventSourcingHandler
    private void handleBasketWasPickedUp(BasketWasPickedUp event) {
        this.id = event.getId();
    }

    @EventSourcingHandler
    private void handleProductAddedToBasket(ProductAddedToBasket event) {
        this.products.add(event.getProductId());
    }

    @EventSourcingHandler
    private void handleProductRemovedFromBasket(ProductRemovedFromBasket event) {
        this.products.remove(event.getProductId());
    }

}
