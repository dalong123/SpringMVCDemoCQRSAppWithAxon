package com.psamatt.basketdemo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author psamatt
 */
public class ProductIdTest {

    @Test
    public void it_should_match_comparison() {
        UUID uuid = UUID.randomUUID();

        assertTrue(new ProductId(uuid).equals(new ProductId(uuid)));
    }

    @Test
    public void it_should_not_match_difference() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        assertFalse(new ProductId(uuid1).equals(new ProductId(uuid2)));
    }
}
