
package com.inventory;

import com.inventory.model.Product;
import com.inventory.model.StockTransaction;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.TransactionRepository;
import com.inventory.service.InventoryService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ProductRepository productRepository =
            new ProductRepository();

    private static final TransactionRepository transactionRepository =
            new TransactionRepository();

    private static final InventoryService inventoryService =
            new InventoryService(productRepository, transactionRepository);

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("======================================");
        System.out.println("     SMART INVENTORY MANAGEMENT");
        System.out.println("======================================");

        while (running) {
            displayMenu();

            try {
                int choice = readInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> viewAllProducts();
                    case 3 -> searchProduct();
                    case 4 -> restockProduct();
                    case 5 -> sellProduct();
                    case 6 -> viewLowStockProducts();
                    case 7 -> viewTransactionHistory();
                    case 8 -> viewInventoryReports();
                    case 0 -> {
                        running = false;
                        System.out.println("Thank you for using Smart Inventory!");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }

            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n------------- MAIN MENU -------------");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product");
        System.out.println("4. Restock Product");
        System.out.println("5. Sell Product");
        System.out.println("6. View Low Stock Products");
        System.out.println("7. View Transaction History");
        System.out.println("8. View Inventory Reports");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
    }

    private static int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private static void addProduct() {
        int id = readInt("Enter product ID: ");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine().trim());

        int quantity = readInt("Enter initial quantity: ");
        int threshold = readInt("Enter reorder threshold: ");

        Product product = new Product(
                id, name, price, quantity, threshold
        );

        productRepository.addProduct(product);

        System.out.println("Product added successfully!");
    }

    private static void viewAllProducts() {
        Collection<Product> products = productRepository.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("\n------------- PRODUCT LIST -------------");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void searchProduct() {
        System.out.print("Enter product name or keyword: ");
        String keyword = scanner.nextLine();

        Collection<Product> products = productRepository.searchByName(keyword);

        if (products.isEmpty()) {
            System.out.println("No matching products found.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void restockProduct() {
        int id = readInt("Enter product ID: ");
        int amount = readInt("Enter quantity to add: ");

        inventoryService.restockProduct(id, amount);

        System.out.println("Stock restocked successfully!");
    }

    private static void sellProduct() {
        int id = readInt("Enter product ID: ");
        int amount = readInt("Enter quantity to sell: ");

        inventoryService.sellProduct(id, amount);

        System.out.println("Sale completed successfully!");
    }

    private static void viewLowStockProducts() {
        Collection<Product> products = productRepository.getLowStockProducts();

        if (products.isEmpty()) {
            System.out.println("No low-stock products.");
            return;
        }

        System.out.println("\n------------- LOW STOCK PRODUCTS -------------");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void viewTransactionHistory() {
        int id = readInt("Enter product ID: ");

        List<StockTransaction> transactions =
                inventoryService.getTransactionHistory(id);

        if (transactions.isEmpty()) {
            System.out.println("No transaction history found.");
            return;
        }

        System.out.println("\n------------- TRANSACTION HISTORY -------------");

        for (StockTransaction transaction : transactions) {
            System.out.println(
                    "Transaction ID: " + transaction.getTransactionId()
                            + " | Product ID: " + transaction.getProductId()
                            + " | Type: " + transaction.getType()
                            + " | Quantity: " + transaction.getQuantity()
                            + " | Date: " + transaction.getTimestamp()
            );
        }
    }


    private static void viewInventoryReports() {

        Collection<Product> products = productRepository.getAllProducts();

        int totalProducts = products.size();
        long totalUnits = 0;
        BigDecimal totalInventoryValue = BigDecimal.ZERO;

        for (Product product : products) {
            totalUnits += product.getQuantity();
            totalInventoryValue =
                    totalInventoryValue.add(product.getStockValue());
        }

        int lowStockCount =
                productRepository.getLowStockProducts().size();

        System.out.println("\n========== INVENTORY REPORT ==========");
        System.out.println("Total Products       : " + totalProducts);
        System.out.println("Total Units in Stock : " + totalUnits);
        System.out.println("Inventory Value      : " + totalInventoryValue);
        System.out.println("Low Stock Products   : " + lowStockCount);
        System.out.println("======================================");
    }

}
