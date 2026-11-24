package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.model.StockTransaction;
import com.inventory.model.TransactionType;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import com.inventory.model.StockTransaction;

public class InventoryService {

    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    public InventoryService(
            ProductRepository productRepository,
            TransactionRepository transactionRepository) {

        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    private int nextTransactionId = 1;
    private int generateTransactionId() {
        int id = nextTransactionId;
        nextTransactionId = Math.incrementExact(nextTransactionId);
        return id;
    }

    public void restockProduct(int productId, int amount) {

        int transactionId = generateTransactionId();

        Product product = productRepository.getProductById(productId);

        product.restock(amount);

        StockTransaction transaction = new StockTransaction(
                transactionId,
                productId,
                TransactionType.RESTOCK,
                amount,
                LocalDateTime.now()
        );

        transactionRepository.addTransaction(transaction);
    }

    public void sellProduct(int productId, int amount) {

        int transactionId = generateTransactionId();

        Product product = productRepository.getProductById(productId);

        product.sell(amount);

        StockTransaction transaction = new StockTransaction(
                transactionId,
                productId,
                TransactionType.SALE,
                amount,
                LocalDateTime.now()
        );

        transactionRepository.addTransaction(transaction);
    }

    public List<StockTransaction> getTransactionHistory(int productId) {
        return transactionRepository.getTransactionsByProductId(productId);
    }

    public List<StockTransaction> getTransactionHistoryBetween(
            int productId,
            LocalDateTime start,
            LocalDateTime end) {

        return transactionRepository.getTransactionsByProductIdBetween(
                productId, start, end
        );
    }

}