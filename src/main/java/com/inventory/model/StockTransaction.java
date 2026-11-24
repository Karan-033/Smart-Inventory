package com.inventory.model;

import java.time.LocalDateTime;

public class StockTransaction {

    private final int transactionId;
    private final int productId;
    private final TransactionType type;
    private final int quantity;
    private final LocalDateTime timestamp;

    public StockTransaction(
            int transactionId,
            int productId,
            TransactionType type,
            int quantity,
            LocalDateTime timestamp) {

        if (transactionId <= 0) {
            throw new IllegalArgumentException(
                    "Transaction ID must be positive"
            );
        }

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Product ID must be positive"
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                    "Transaction type cannot be null"
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Transaction quantity must be positive"
            );
        }

        if (timestamp == null) {
            throw new IllegalArgumentException(
                    "Transaction timestamp cannot be null"
            );
        }

        this.transactionId = transactionId;
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getProductId() {
        return productId;
    }

    public TransactionType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}