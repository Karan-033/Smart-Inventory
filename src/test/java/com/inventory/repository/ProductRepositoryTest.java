package com.inventory.repository;

import com.inventory.model.Product;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ProductRepositoryTest {
    @Test
    void shouldSearchProductsByName() {

        ProductRepository repository = new ProductRepository();

        repository.addProduct(new Product(
                101, "Wireless Mouse", new BigDecimal("500"), 10, 2
        ));

        repository.addProduct(new Product(
                102, "Mechanical Keyboard", new BigDecimal("1000"), 5, 1
        ));

        repository.addProduct(new Product(
                103, "Wireless Headphones", new BigDecimal("1500"), 8, 2
        ));

        Collection<Product> result = repository.searchByName("wireless");

        assertEquals(2, result.size());
    }

    @Test
    void shouldSearchProductsWithoutCaseSensitivity() {

        ProductRepository repository = new ProductRepository();

        repository.addProduct(new Product(
                101, "Wireless Mouse", new BigDecimal("500"), 10, 2
        ));

        Collection<Product> result = repository.searchByName("MOUSE");

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyCollectionWhenNoProductMatches() {

        ProductRepository repository = new ProductRepository();

        Collection<Product> result = repository.searchByName("Laptop");

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldRejectBlankSearchKeyword() {

        ProductRepository repository = new ProductRepository();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.searchByName(" ")
        );
    }

    @Test
    void shouldSortProductsByName() {

        ProductRepository repository = new ProductRepository();

        Product p1 = new Product(
                101, "Wireless Mouse", new BigDecimal("500"), 10, 2
        );

        Product p2 = new Product(
                102, "Keyboard", new BigDecimal("1000"), 5, 1
        );

        Product p3 = new Product(
                103, "Adapter", new BigDecimal("200"), 20, 5
        );

        repository.addProduct(p1);
        repository.addProduct(p2);
        repository.addProduct(p3);

        List<Product> result = repository.sortByName();

        assertEquals("Adapter", result.get(0).getName());
        assertEquals("Keyboard", result.get(1).getName());
        assertEquals("Wireless Mouse", result.get(2).getName());
    }

    @Test
    void shouldSortProductsByPrice() {

        ProductRepository repository = new ProductRepository();

        repository.addProduct(new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        ));

        repository.addProduct(new Product(
                102, "Keyboard", new BigDecimal("1000"), 5, 1
        ));

        repository.addProduct(new Product(
                103, "Adapter", new BigDecimal("200"), 20, 5
        ));

        List<Product> result = repository.sortByPrice();

        assertEquals("Adapter", result.get(0).getName());
        assertEquals("Mouse", result.get(1).getName());
        assertEquals("Keyboard", result.get(2).getName());
    }

    @Test
    void shouldSortProductsByQuantityDescending() {

        ProductRepository repository = new ProductRepository();

        repository.addProduct(new Product(
                101, "Mouse", new BigDecimal("500"), 20, 2
        ));

        repository.addProduct(new Product(
                102, "Keyboard", new BigDecimal("1000"), 5, 1
        ));

        repository.addProduct(new Product(
                103, "Adapter", new BigDecimal("200"), 50, 5
        ));

        List<Product> result = repository.sortByQuantity();

        assertEquals("Adapter", result.get(0).getName());
        assertEquals("Mouse", result.get(1).getName());
        assertEquals("Keyboard", result.get(2).getName());
    }

    @Test
    void shouldReturnOnlyLowStockProducts() {

        ProductRepository repository = new ProductRepository();

        Product mouse = new Product(
                101, "Mouse", new BigDecimal("500"), 5, 10
        );

        Product keyboard = new Product(
                102, "Keyboard", new BigDecimal("1000"), 20, 5
        );

        Product adapter = new Product(
                103, "Adapter", new BigDecimal("200"), 3, 3
        );

        repository.addProduct(mouse);
        repository.addProduct(keyboard);
        repository.addProduct(adapter);

        List<Product> result = repository.getLowStockProducts();

        assertEquals(2, result.size());
        assertTrue(result.contains(mouse));
        assertTrue(result.contains(adapter));
        assertFalse(result.contains(keyboard));
    }
}