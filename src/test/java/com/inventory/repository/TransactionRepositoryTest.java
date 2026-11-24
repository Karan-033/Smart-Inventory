package com.inventory.repository;

import com.inventory.model.StockTransaction;
import com.inventory.model.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    @Test
    void shouldAddTransactionSuccessfully() {

        TransactionRepository repository = new TransactionRepository();

        StockTransaction transaction = new StockTransaction(
                1, 101, TransactionType.RESTOCK,
                10, LocalDateTime.now()
        );

        repository.addTransaction(transaction);

        assertEquals(1, repository.getAllTransactions().size());
    }

    @Test
    void shouldReturnTransactionsForSpecificProduct() {

        TransactionRepository repository = new TransactionRepository();

        StockTransaction transaction1 = new StockTransaction(
                1, 101, TransactionType.RESTOCK,
                10, LocalDateTime.now()
        );

        StockTransaction transaction2 = new StockTransaction(
                2, 102, TransactionType.SALE,
                5, LocalDateTime.now()
        );

        repository.addTransaction(transaction1);
        repository.addTransaction(transaction2);

        List<StockTransaction> result =
                repository.getTransactionsByProductId(101);

        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getProductId());
    }

    @Test
    void shouldRejectNullTransaction() {

        TransactionRepository repository = new TransactionRepository();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.addTransaction(null)
        );
    }
}