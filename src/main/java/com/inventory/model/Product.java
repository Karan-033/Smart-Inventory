package com.inventory.model;

import java.math.BigDecimal;
public class Product {
    private final int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int reorderThreshold;

    public Product(int id, String name, BigDecimal price, int quantity, int reorderThreshold) {
        if(id <= 0){
            throw new IllegalArgumentException("id must be greater than 0");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Product name cannot be Blank");
        }
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("price cannot be negative");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        if(reorderThreshold < 0){
            throw new IllegalArgumentException("reorderThreshold cannot be negative");
        }

        this.id = id;
        this.name = name.trim();
        this.price = price;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
    }

    //Getter implement to access private field
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getReorderThreshold(){
        return reorderThreshold;
    }

    //Update Product Name
    public void updateName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Product name cannot be Blank");
        }
        this.name = name.trim();
    }

    //Update Product Price
    public void updatePrice(BigDecimal price){
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("price cannot be negative");
        }
        this.price = price;
    }

    //Restock a Product
    public void restock(int amount){
        if(amount <= 0){
            throw new IllegalArgumentException("restock amount must be positive");
        }
        this.quantity = Math.addExact(this.quantity, amount);
    }

    //Sell A Product
    public void sell(int amount){
        if(amount <= 0){
            throw new IllegalArgumentException("sell amount must be positive");
        }
        if(amount > quantity){
            throw new IllegalArgumentException("Insufficient Stock Available");
        }
        this.quantity -= amount;
    }

    //Low-Stock Detection
    public boolean isLowStock(){
        return quantity <= reorderThreshold;
    }

    //Calculate Stock Value
    public BigDecimal getStockValue(){
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Product other)){
            return false;
        }
        return id == other.id;
    }
    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }

    @Override
    public String toString(){
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", reorderThreshold=" + reorderThreshold +
                '}';
    }

}
