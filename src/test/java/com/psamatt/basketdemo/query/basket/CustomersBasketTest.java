package com.psamatt.basketdemo.query.basket;

import com.psamatt.basketdemo.domain.BasketId;
import com.psamatt.basketdemo.domain.ProductId;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author psamatt
 */
public class CustomersBasketTest {

    @Test
    public void it_should_add_product_to_basket() {
        CustomersBasket basket = new CustomersBasket(BasketId.generate());

        assertEquals(0, basket.getNumberOfProducts());

        basket.addProduct(new ProductId(UUID.randomUUID()), "Foo");

        assertEquals(1, basket.getNumberOfProducts());
    }

    @Test
    public void it_should_add_two_products_to_basket() {
        CustomersBasket basket = new CustomersBasket(BasketId.generate());

        assertEquals(0, basket.getNumberOfProducts());

        basket.addProduct(new ProductId(UUID.randomUUID()), "Foo");
        basket.addProduct(new ProductId(UUID.randomUUID()), "Bar");

        assertEquals(2, basket.getNumberOfProducts());
    }

    @Test
    public void it_should_remove_product_from_basket() {
        ProductId productId = new ProductId(UUID.randomUUID());
        CustomersBasket basket = new CustomersBasket(BasketId.generate());
        basket.addProduct(productId, "Foo");

        assertEquals(1, basket.getNumberOfProducts());

        basket.removeProduct(productId);

        assertEquals(0, basket.getNumberOfProducts());
    }
}
