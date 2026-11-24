package com.inventory.repository;

import com.inventory.model.StockTransaction;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

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

    public List<StockTransaction> getTransactionsBetween(
            LocalDateTime start,
            LocalDateTime end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        validateDateRange(start, end);

        List<StockTransaction> result = new ArrayList<>();

        for (StockTransaction transaction : transactions) {
            if (!transaction.getTimestamp().isBefore(start)
                    && !transaction.getTimestamp().isAfter(end)) {
                result.add(transaction);
            }
        }

        return result;
    }

    public List<StockTransaction> getTransactionsByProductIdBetween(
            int productId,
            LocalDateTime start,
            LocalDateTime end) {

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive");
        }

        validateDateRange(start, end);

        List<StockTransaction> result = new ArrayList<>();

        for (StockTransaction transaction : transactions) {
            if (transaction.getProductId() == productId
                    && !transaction.getTimestamp().isBefore(start)
                    && !transaction.getTimestamp().isAfter(end)) {
                result.add(transaction);
            }
        }

        return result;
    }

    private void validateDateRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(
                    "Start date cannot be after end date"
            );
        }
    }

}