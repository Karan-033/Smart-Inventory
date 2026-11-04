package com.inventory.repository;

import com.inventory.model.Product;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    @Test
    void shouldAddProductSccessfully(){
        ProductRepository repository = new ProductRepository();

        Product product = new Product(
                101,
                "Wireless Mouse",
                new BigDecimal("799.00"),
                50,
                10
        );
        assertDoesNotThrow(() -> repository.addProduct(product));
    }

    @Test
    void shouldRejectDuplicateProductId(){
        ProductRepository repository = new ProductRepository();
        Product product1 = new Product(101,"Mouse",new BigDecimal("500"),10,2);
        Product product2 = new Product(101,"Keyboard",new BigDecimal("1000"),5,1);
        repository.addProduct(product1);
        assertThrows(IllegalArgumentException.class,() -> repository.addProduct(product2));
    }
}