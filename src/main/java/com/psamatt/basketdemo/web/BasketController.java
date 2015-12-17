package com.psamatt.basketdemo.web;

import com.psamatt.basketdemo.domain.*;
import com.psamatt.basketdemo.query.basket.CustomersBasket;
import com.psamatt.basketdemo.query.basket.CustomersBasketRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author psamatt
 */
@RestController
@RequestMapping(value = "/basket")
public class BasketController {

    private CustomersBasketRepository customersBasketRepository;
    private CommandGateway commandGateway;

    @Autowired
    public BasketController(CustomersBasketRepository customersBasketRepository, CommandGateway commandGateway) {
        this.customersBasketRepository = customersBasketRepository;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/pickup", method = RequestMethod.POST)
    public @ResponseBody
    String pickUpBasket() {

        BasketId basketId = BasketId.generate();

        commandGateway.send(
                new PickUpBasket(
                        basketId
                )
        );

        return basketId.toString();
    }

    @RequestMapping(value = "/{basketId}/view", method = RequestMethod.GET, produces = "application/json;")
    public @ResponseBody
    CustomersBasket viewBasket(@PathVariable String basketId) {
        return customersBasketRepository.findOne(basketId);
    }

    @RequestMapping(value = "/{basketId}/product/{productId}/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addProductToBasket(@PathVariable String basketId, @PathVariable String productId, @RequestParam(value="productName", required=true) String productName) {
        commandGateway.send(
                new AddProductToBasket(
                        BasketId.fromString(basketId),
                        ProductId.fromString(productId),
                        productName
                )
        );
    }

    @RequestMapping(value = "/{basketId}/product/{productId}/remove", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeProductFromBasket(@PathVariable String basketId, @PathVariable String productId) {
        commandGateway.send(
                new RemoveProductFromBasket(
                        BasketId.fromString(basketId),
                        ProductId.fromString(productId)
                )
        );
    }
}
