package com.psamatt.basketdemo.domain;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author psamatt
 */
@Component
public class BasketCommandHandler {

    private Repository<Basket> repository;

    @Autowired
    public BasketCommandHandler(Repository<Basket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(PickUpBasket command) {
        Basket basket = Basket.pickUp(command.getId());

        repository.add(basket);
    }

    @CommandHandler
    public void handle(AddProductToBasket command) {
        Basket basket = repository.load(command.getBasketId());
        basket.addProduct(command.getProductId(), command.getProductName());
    }

    @CommandHandler
    public void handle(RemoveProductFromBasket command) {
        Basket basket = repository.load(command.getBasketId());
        basket.removeProduct(command.getProductId());
    }
}
