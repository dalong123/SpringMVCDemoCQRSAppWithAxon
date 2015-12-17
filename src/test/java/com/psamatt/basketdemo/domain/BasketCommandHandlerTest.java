package com.psamatt.basketdemo.domain;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * @author psamatt
 */
public class BasketCommandHandlerTest {

    private FixtureConfiguration<Basket> fixture;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Basket.class);
        BasketCommandHandler basketCommandHandler = new BasketCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(basketCommandHandler);
    }

    @Test
    public void it_should_pickup_basket() {
        BasketId id = BasketId.generate();

        fixture.given()
                .when(new PickUpBasket(id))
                .expectVoidReturnType()
                .expectEvents(new BasketWasPickedUp(id));
    }

    @Test
    public void it_should_add_product_to_basket() {
        BasketId id = BasketId.generate();
        ProductId productId = new ProductId(UUID.randomUUID());

        fixture.given(new BasketWasPickedUp(id))
                .when(new AddProductToBasket(id, productId, "Apples"))
                .expectVoidReturnType()
                .expectEvents(new ProductAddedToBasket(id, productId, "Apples"));
    }

    @Test
    public void it_should_not_add_duplicate_product_to_basket() {
        BasketId id = BasketId.generate();
        ProductId productId = new ProductId(UUID.randomUUID());

        fixture.given(new BasketWasPickedUp(id), new ProductAddedToBasket(id, productId, "Apples"))
                .when(new AddProductToBasket(id, productId, "Apples"))
                .expectVoidReturnType()
                .expectEvents();
    }

    @Test
    public void it_should_only_add_three_products_to_basket() {
        BasketId id = BasketId.generate();
        ProductId productId1 = new ProductId(UUID.randomUUID());
        ProductId productId2 = new ProductId(UUID.randomUUID());
        ProductId productId3 = new ProductId(UUID.randomUUID());
        ProductId productId4 = new ProductId(UUID.randomUUID());

        fixture.given(
                    new BasketWasPickedUp(id),
                    new ProductAddedToBasket(id, productId1, "Apples"),
                    new ProductAddedToBasket(id, productId2, "Bananas"),
                    new ProductAddedToBasket(id, productId3, "Pears")
                )
                .when(new AddProductToBasket(id, productId4, "Oranges"))
                .expectException(BasketCanOnlyContainThreeProducts.class);
    }

    @Test
    public void it_should_remove_added_product_from_basket() {

        BasketId id = BasketId.generate();
        ProductId productId = new ProductId(UUID.randomUUID());

        fixture.given(
                    new BasketWasPickedUp(id),
                    new ProductAddedToBasket(id, productId, "Apples")
                )
                .when(new RemoveProductFromBasket(id, productId))
                .expectVoidReturnType()
                .expectEvents(new ProductRemovedFromBasket(id, productId));
    }

    @Test
    public void it_ignores_removal_of_product_that_hasnt_been_added_to_basket() {

        BasketId id = BasketId.generate();
        ProductId productId = new ProductId(UUID.randomUUID());

        fixture.given(
                    new BasketWasPickedUp(id)
                )
                .when(new RemoveProductFromBasket(id, productId))
                .expectVoidReturnType()
                .expectEvents();
    }
}
