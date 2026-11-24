package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import com.inventory.repository.TransactionRepository;
import java.util.List;
import com.inventory.model.StockTransaction;
import com.inventory.model.TransactionType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class InventoryServiceTest {

    @Test
    void shouldRestockProductSuccessfully() {

        ProductRepository productRepository = new ProductRepository();

        TransactionRepository transactionRepository =
                new TransactionRepository();

        InventoryService service = new InventoryService(
                productRepository,
                transactionRepository
        );

        Product product = new Product(
                101,
                "Mouse",
                new BigDecimal("500"),
                10,
                2
        );

        // Add product to the SAME repository
        productRepository.addProduct(product);

        service.restockProduct(101, 5);

        assertEquals(15, product.getQuantity());
    }

    @Test
    void shouldRejectInvalidRestockAmount() {

        ProductRepository repository = new ProductRepository();
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository =
                new TransactionRepository();

        InventoryService service = new InventoryService(
                productRepository,
                transactionRepository
        );

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
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository =
                new TransactionRepository();

        InventoryService service = new InventoryService(
                productRepository,
                transactionRepository
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.restockProduct(999, 5)
        );
    }

    @Test
    void shouldSellProductSuccessfully() {

        ProductRepository productRepository = new ProductRepository();

        TransactionRepository transactionRepository =
                new TransactionRepository();

        InventoryService service = new InventoryService(
                productRepository,
                transactionRepository
        );

        Product product = new Product(
                101,
                "Mouse",
                new BigDecimal("500"),
                10,
                2
        );

        // Add product to the SAME repository
        productRepository.addProduct(product);

        service.restockProduct(101, 5);

        assertEquals(15, product.getQuantity());
    }

    @Test
    void shouldRejectSaleWhenStockIsInsufficient() {

        ProductRepository repository = new ProductRepository();
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository =
                new TransactionRepository();

        InventoryService service = new InventoryService(
                productRepository,
                transactionRepository
        );

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

    @Test
    void shouldRejectRestockWhenIntegerOverflows() {

        Product product = new Product(
                101,
                "Mouse",
                new BigDecimal("500"),
                Integer.MAX_VALUE,
                0
        );

        assertThrows(
                ArithmeticException.class,
                () -> product.restock(1)
        );

        assertEquals(Integer.MAX_VALUE, product.getQuantity());
    }

    @Test
    void shouldRejectZeroRestockAmount() {

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> product.restock(0)
        );

        assertEquals(10, product.getQuantity());
    }

    @Test
    void shouldRejectNegativeRestockAmount() {

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> product.restock(-5)
        );

        assertEquals(10, product.getQuantity());
    }

    @Test
    void shouldRejectZeroSaleAmount() {

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> product.sell(0)
        );

        assertEquals(10, product.getQuantity());
    }

    @Test
    void shouldRejectNegativeSaleAmount() {

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> product.sell(-5)
        );

        assertEquals(10, product.getQuantity());
    }

    @Test
    void shouldAllowSellingAllAvailableStock() {

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );

        product.sell(10);

        assertEquals(0, product.getQuantity());
    }

    @Test
    void shouldRecordRestockTransaction() {
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        Product product = new Product(
                101, "Mouse", new BigDecimal("500"), 10, 2
        );
        productRepository.addProduct(product);

        InventoryService service = new InventoryService(
                productRepository, transactionRepository
        );

        service.restockProduct(101, 5);

        List<StockTransaction> transactions =
                transactionRepository.getAllTransactions();

        assertEquals(1, transactions.size());

        StockTransaction transaction = transactions.get(0);
        assertEquals(101, transaction.getProductId());
        assertEquals(TransactionType.RESTOCK, transaction.getType());
        assertEquals(5, transaction.getQuantity());
    }

    @Test
    void shouldRecordSaleTransaction() {
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        Product product = new Product(
                102, "Keyboard", new BigDecimal("1200"), 20, 5
        );
        productRepository.addProduct(product);

        InventoryService service = new InventoryService(
                productRepository, transactionRepository
        );

        service.sellProduct(102, 3);

        List<StockTransaction> transactions =
                transactionRepository.getAllTransactions();

        assertEquals(1, transactions.size());

        StockTransaction transaction = transactions.get(0);
        assertEquals(102, transaction.getProductId());
        assertEquals(TransactionType.SALE, transaction.getType());
        assertEquals(3, transaction.getQuantity());
    }

    @Test
    void shouldNotRecordTransactionWhenSaleFails() {
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        Product product = new Product(
                103, "Monitor", new BigDecimal("10000"), 5, 1
        );
        productRepository.addProduct(product);

        InventoryService service = new InventoryService(
                productRepository, transactionRepository
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.sellProduct(103, 10)
        );

        assertEquals(5, product.getQuantity());
        assertEquals(0, transactionRepository.getAllTransactions().size());
    }

    @Test
    void shouldGenerateUniqueTransactionIds() {
        ProductRepository productRepository = new ProductRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        Product product = new Product(
                104, "Headphones", new BigDecimal("2000"), 20, 3
        );
        productRepository.addProduct(product);

        InventoryService service = new InventoryService(
                productRepository, transactionRepository
        );

        service.restockProduct(104, 5);
        service.sellProduct(104, 2);

        List<StockTransaction> transactions =
                transactionRepository.getAllTransactions();

        assertEquals(2, transactions.size());

        assertNotEquals(
                transactions.get(0).getTransactionId(),
                transactions.get(1).getTransactionId()
        );
    }
}