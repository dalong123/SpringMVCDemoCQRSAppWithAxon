package com.psamatt.basketdemo.query.basket;

import com.psamatt.basketdemo.domain.BasketWasPickedUp;
import com.psamatt.basketdemo.domain.ProductAddedToBasket;
import com.psamatt.basketdemo.domain.ProductRemovedFromBasket;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author psamatt
 */
@Component
public class CustomersBasketProjector {

    private CustomersBasketRepository customersBasketRepository;

    @Autowired
    public void setCustomersBasketRepository(CustomersBasketRepository customersBasketRepository) {
        this.customersBasketRepository = customersBasketRepository;
    }

    @EventHandler
    void on(BasketWasPickedUp event) {
        CustomersBasket basket = new CustomersBasket(event.getId());
        customersBasketRepository.save(basket);
    }

    @EventHandler
    void on(ProductAddedToBasket event) {
        CustomersBasket customersBasket = customersBasketRepository.findOne(event.getBasketId().toString());
        customersBasket.addProduct(event.getProductId(), event.getProductName());

        customersBasketRepository.save(customersBasket);
    }

    @EventHandler
    void on(ProductRemovedFromBasket event) {
        CustomersBasket customersBasket = customersBasketRepository.findOne(event.getBasketId().toString());
        customersBasket.removeProduct(event.getProductId());

        customersBasketRepository.save(customersBasket);
    }
}
