package com.inventory.repository;

import com.inventory.model.Product;
import java.util.HashMap;
import java.util.Map;
public class ProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(products.containsKey(product.getId())){
            throw new IllegalArgumentException("Product with id " + product.getId() + " already exists");
        }
        products.put(product.getId(), product);
    }
}
