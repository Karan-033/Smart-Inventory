package com.inventory.repository;

import com.inventory.model.StockTransaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private final List<StockTransaction> transactions =
            new ArrayList<>();

    public void addTransaction(StockTransaction transaction) {

        if (transaction == null) {
            throw new IllegalArgumentException(
                    "Transaction cannot be null"
            );
        }

        transactions.add(transaction);
    }

    public List<StockTransaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<StockTransaction> getTransactionsByProductId(int productId) {

        List<StockTransaction> result = new ArrayList<>();

        for (StockTransaction transaction : transactions) {

            if (transaction.getProductId() == productId) {
                result.add(transaction);
            }
        }

        return result;
    }
}