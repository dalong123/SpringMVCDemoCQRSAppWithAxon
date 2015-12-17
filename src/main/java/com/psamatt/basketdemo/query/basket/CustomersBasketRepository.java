package com.psamatt.basketdemo.query.basket;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author psamatt
 */
public interface CustomersBasketRepository extends ElasticsearchRepository<CustomersBasket, String> {
}
