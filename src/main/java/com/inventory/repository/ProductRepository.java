package com.inventory.repository;

import com.inventory.model.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


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

    public Collection<Product> searchByName(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                    "Search keyword cannot be null or blank"
            );
        }

        List<Product> result = new ArrayList<>();

        String searchKeyword = keyword.trim().toLowerCase();

        for (Product product : products.values()) {

            if (product.getName().toLowerCase().contains(searchKeyword)) {
                result.add(product);
            }
        }

        return result;
    }

    public List<Product> sortByName() {

        List<Product> sortedProducts =
                new ArrayList<>(products.values());

        sortedProducts.sort(
                Comparator.comparing(Product::getName)
        );

        return sortedProducts;
    }
    //sort products by price
    public List<Product> sortByPrice() {

        List<Product> sortedProducts =
                new ArrayList<>(products.values());

        sortedProducts.sort(
                Comparator.comparing(Product::getPrice)
        );

        return sortedProducts;
    }

    //sort product by quantity
    public List<Product> sortByQuantity() {

        List<Product> sortedProducts =
                new ArrayList<>(products.values());

        sortedProducts.sort(
                Comparator.comparingInt(Product::getQuantity).reversed()
        );

        return sortedProducts;
    }
}
