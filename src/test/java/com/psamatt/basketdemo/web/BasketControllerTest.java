package com.psamatt.basketdemo.web;

import com.psamatt.basketdemo.Application;
import com.psamatt.basketdemo.query.basket.CustomersBasketRepository;
import com.psamatt.basketdemo.query.basket.ProductInCustomersBasket;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.json.JSONObject;
import org.json.JSONArray;


/**
 * @author psamatt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BasketControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(
                new BasketController(
                        webApplicationContext.getBean(CustomersBasketRepository.class),
                        webApplicationContext.getBean(CommandGateway.class)
                )
            ).build();
    }

    @Test
    public void it_should_pickup_new_basket_and_view_empty_basket() throws Exception {

        MvcResult result = mockMvc.perform(post("/basket/pickup"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String basketId = result.getResponse().getContentAsString();

        assertTrue(basketId.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));

        mockMvc.perform(get(String.format("/basket/%s/view", basketId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(basketId));
    }

    @Test
    public void it_should_add_product_to_basket() throws Exception {
        MvcResult result = mockMvc.perform(post("/basket/pickup"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String basketId = result.getResponse().getContentAsString();
        String productId = UUID.randomUUID().toString();
        String productName = "Foo";

        mockMvc.perform(
                    post(String.format("/basket/%s/product/%s/add?productName=%s", basketId, productId, productName))
                )
                .andExpect(status().is2xxSuccessful());

        MvcResult basketContents = mockMvc.perform(get(String.format("/basket/%s/view", basketId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(basketId))
                .andReturn();

        JSONObject json = new JSONObject(basketContents.getResponse().getContentAsString());

        JSONArray expectedJson = new JSONArray();
        expectedJson.put(new JSONObject(new ProductInCustomersBasket(productId, productName)));

        assertEquals(json.get("products").toString(), expectedJson.toString());
    }

    @Test
    public void it_should_remove_product_to_basket() throws Exception {
        MvcResult result = mockMvc.perform(post("/basket/pickup"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String basketId = result.getResponse().getContentAsString();
        String productId = UUID.randomUUID().toString();
        String productName = "Foo";

        mockMvc.perform(
                    post(String.format("/basket/%s/product/%s/add?productName=%s", basketId, productId, productName))
                )
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                    post(String.format("/basket/%s/product/%s/remove", basketId, productId))
                )
                .andExpect(status().is2xxSuccessful());

        MvcResult basketContents = mockMvc.perform(get(String.format("/basket/%s/view", basketId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(basketId))
                .andReturn();

        JSONObject json = new JSONObject(basketContents.getResponse().getContentAsString());
        JSONArray expectedJson = new JSONArray();

        assertEquals(json.get("products").toString(), expectedJson.toString());
    }
}
