package com.inventory.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StockTransactionTest {

    @Test
    void shouldCreateValidStockTransaction() {

        LocalDateTime now = LocalDateTime.now();

        StockTransaction transaction = new StockTransaction(
                1,
                101,
                TransactionType.RESTOCK,
                10,
                now
        );

        assertEquals(1, transaction.getTransactionId());
        assertEquals(101, transaction.getProductId());
        assertEquals(TransactionType.RESTOCK, transaction.getType());
        assertEquals(10, transaction.getQuantity());
        assertEquals(now, transaction.getTimestamp());
    }

    @Test
    void shouldRejectNegativeTransactionQuantity() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new StockTransaction(
                        1,
                        101,
                        TransactionType.SALE,
                        -5,
                        LocalDateTime.now()
                )
        );
    }
}