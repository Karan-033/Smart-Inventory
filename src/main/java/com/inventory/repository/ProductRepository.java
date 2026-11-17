package com.inventory.repository;

import com.inventory.model.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
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

    public Product getProductById(int id) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException(
                    "Product with ID " + id + " not found"
            );
        }

        return product;
    }

    public Collection<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void deleteProduct(int id) {

        if (!products.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Product with ID " + id + " not found"
            );
        }

        products.remove(id);
    }
}
