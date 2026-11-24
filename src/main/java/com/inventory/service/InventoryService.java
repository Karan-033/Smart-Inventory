package com.inventory.service;

import com.inventory.repository.ProductRepository;
import com.inventory.model.Product;

public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void restockProduct(int productId, int amount) {

        Product product = productRepository.getProductById(productId);

        product.restock(amount);
    }

    public void sellProduct(int productId, int amount) {

        Product product = productRepository.getProductById(productId);

        product.sell(amount);
    }

}