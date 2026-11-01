package com.inventory.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void shouldCreateProductWithValidDetails(){
        Product product = new Product(
                101,
                "Wireless Mouse",
                new BigDecimal("799.00"),
                50,
                10
        );
        assertEquals(101,product.getId());
        assertEquals("Wireless Mouse", product.getName());
        assertEquals(0, new BigDecimal("799.00").compareTo(product.getPrice()));
        assertEquals(50, product.getQuantity());
        assertEquals(10,product.getReorderThreshold());
    }

    @Test
    void shouldRejectBlankProductName(){
        assertThrows(IllegalArgumentException.class, ()-> new Product(101," ", new BigDecimal("500"),10,2));
    }

    @Test
    void shouldRejectNegativePrice(){
        assertThrows(IllegalArgumentException.class, ()-> new Product(101,"Mouse", new BigDecimal("-10"),10,2));
    }

    @Test
    void shouldRejectNegativeQuantity(){
        assertThrows(IllegalArgumentException.class, ()-> new Product(101, "Mouse", new BigDecimal("500"), -1,2));
    }

    @Test
    void shouldIncreaseQuantityWhenRestocked() {
        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        product.restock(5);

        assertEquals(15, product.getQuantity());
    }

    @Test
    void shouldDecreaseQuantityWhenSold() {
        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        product.sell(3);

        assertEquals(7, product.getQuantity());
    }

    @Test
    void shouldRejectSaleWhenStockIsInsufficient() {
        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 5, 2
        );

        assertThrows(IllegalArgumentException.class, () ->
                product.sell(10)
        );

        assertEquals(5, product.getQuantity());
    }

    @Test
    void productsWithSameIdShouldBeEqual() {
        Product product1 = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        Product product2 = new Product(
                101, "Wireless Mouse", new BigDecimal("700"), 20, 5
        );

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }
}
