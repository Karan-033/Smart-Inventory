package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Test
    void shouldRestockProductSuccessfully() {

        ProductRepository repository = new ProductRepository();
        InventoryService service = new InventoryService(repository);

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        repository.addProduct(product);

        service.restockProduct(101, 5);

        assertEquals(15, product.getQuantity());
    }

    @Test
    void shouldRejectInvalidRestockAmount() {

        ProductRepository repository = new ProductRepository();
        InventoryService service = new InventoryService(repository);

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        repository.addProduct(product);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.restockProduct(101, -5)
        );

        assertEquals(10, product.getQuantity());
    }

    @Test
    void shouldRejectRestockForMissingProduct() {

        ProductRepository repository = new ProductRepository();
        InventoryService service = new InventoryService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.restockProduct(999, 5)
        );
    }

    @Test
    void shouldSellProductSuccessfully() {

        ProductRepository repository = new ProductRepository();
        InventoryService service = new InventoryService(repository);

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        repository.addProduct(product);

        service.sellProduct(101, 3);

        assertEquals(7, product.getQuantity());
    }

    @Test
    void shouldRejectSaleWhenStockIsInsufficient() {

        ProductRepository repository = new ProductRepository();
        InventoryService service = new InventoryService(repository);

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 5, 2
        );

        repository.addProduct(product);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.sellProduct(101, 10)
        );

        assertEquals(5, product.getQuantity());
    }
}