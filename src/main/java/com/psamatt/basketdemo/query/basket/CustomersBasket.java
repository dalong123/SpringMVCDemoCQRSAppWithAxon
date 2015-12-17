package com.psamatt.basketdemo.query.basket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.psamatt.basketdemo.domain.BasketId;
import com.psamatt.basketdemo.domain.ProductId;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author psamatt
 */
@Document(indexName="customers-basket-index", type="customers-basket-type")
public class CustomersBasket {

    @Id
    private String id;
    private List<ProductInCustomersBasket> products = new ArrayList<>();

    public CustomersBasket(BasketId id) {
        this.id = id.toString();
    }

    @JsonCreator
    public CustomersBasket(@JsonProperty("id") String id, @JsonProperty("products") ArrayList<ProductInCustomersBasket> products) {
        this.id = id;
        this.products = products;
    }

    /**
     * Add product
     *
     * @param productId
     * @param productName
     */
    public void addProduct(ProductId productId, String productName) {
        this.products.add(new ProductInCustomersBasket(productId.toString(), productName));
    }

    /**
     * Remove product
     *
     * @param productId
     */
    public void removeProduct(ProductId productId) {
        for (ProductInCustomersBasket p: products) {
            if (0 == p.getProductId().compareTo(productId.toString())) {
                products.remove(p);
                break;
            }
        }
    }

    public String getId() {
        return id;
    }

    public List<ProductInCustomersBasket> getProducts() {
        return products;
    }

    public int getNumberOfProducts() {
        return products.size();
    }
}
