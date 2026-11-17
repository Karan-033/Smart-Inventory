package com.inventory.repository;

import com.inventory.model.Product;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    @Test
    void shouldDeleteProductSuccessfully() {

        ProductRepository repository = new ProductRepository();

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        repository.addProduct(product);
        repository.deleteProduct(101);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.getProductById(101)
        );
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingProduct() {

        ProductRepository repository = new ProductRepository();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.deleteProduct(999)
        );
    }
}