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

    @Test
    void shouldGetTransactionsBetweenDates() {
        TransactionRepository repository = new TransactionRepository();

        LocalDateTime start = LocalDateTime.of(2026, 11, 20, 10, 0);
        LocalDateTime middle = LocalDateTime.of(2026, 11, 21, 12, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 22, 18, 0);
        LocalDateTime outside = LocalDateTime.of(2026, 11, 23, 10, 0);

        repository.addTransaction(new StockTransaction(
                1, 101, TransactionType.RESTOCK, 5, start
        ));
        repository.addTransaction(new StockTransaction(
                2, 101, TransactionType.SALE, 2, middle
        ));
        repository.addTransaction(new StockTransaction(
                3, 102, TransactionType.RESTOCK, 4, end
        ));
        repository.addTransaction(new StockTransaction(
                4, 101, TransactionType.SALE, 1, outside
        ));

        List<StockTransaction> result =
                repository.getTransactionsBetween(start, end);

        assertEquals(3, result.size());
    }

    @Test
    void shouldRejectNullStartDate() {
        TransactionRepository repository = new TransactionRepository();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.getTransactionsBetween(
                        null, LocalDateTime.now()
                )
        );
    }

    @Test
    void shouldRejectInvalidDateRange() {
        TransactionRepository repository = new TransactionRepository();

        LocalDateTime start = LocalDateTime.of(2026, 11, 22, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 20, 10, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.getTransactionsBetween(start, end)
        );
    }

    @Test
    void shouldGetTransactionsByProductIdBetweenDates() {
        TransactionRepository repository = new TransactionRepository();

        LocalDateTime start = LocalDateTime.of(2026, 11, 20, 10, 0);
        LocalDateTime middle = LocalDateTime.of(2026, 11, 21, 12, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 22, 18, 0);

        repository.addTransaction(new StockTransaction(
                1, 101, TransactionType.RESTOCK, 5, start
        ));
        repository.addTransaction(new StockTransaction(
                2, 101, TransactionType.SALE, 2, middle
        ));
        repository.addTransaction(new StockTransaction(
                3, 102, TransactionType.RESTOCK, 4, middle
        ));
        repository.addTransaction(new StockTransaction(
                4, 101, TransactionType.SALE, 1, end
        ));

        List<StockTransaction> result =
                repository.getTransactionsByProductIdBetween(101, start, end);

        assertEquals(3, result.size());

        for (StockTransaction transaction : result) {
            assertEquals(101, transaction.getProductId());
        }
    }

    @Test
    void shouldRejectInvalidProductIdForDateFilter() {
        TransactionRepository repository = new TransactionRepository();

        LocalDateTime start = LocalDateTime.of(2026, 11, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 22, 18, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.getTransactionsByProductIdBetween(
                        0, start, end
                )
        );
    }

    @Test
    void shouldRejectNullDatesForProductDateFilter() {
        TransactionRepository repository = new TransactionRepository();

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.getTransactionsByProductIdBetween(
                        101, null, LocalDateTime.now()
                )
        );
    }

    @Test
    void shouldRejectReversedDatesForProductDateFilter() {
        TransactionRepository repository = new TransactionRepository();

        LocalDateTime start = LocalDateTime.of(2026, 11, 22, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 11, 20, 10, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.getTransactionsByProductIdBetween(
                        101, start, end
                )
        );
    }


}