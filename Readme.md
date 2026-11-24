# 📦 Smart Inventory and Stock Management System

A Java-based Command-Line Inventory Management System that helps manage products, track stock levels, record inventory transactions, and generate inventory reports.

## 📌 Project Overview

The Smart Inventory and Stock Management System is developed using Java 21. It provides a menu-driven CLI interface for managing inventory efficiently.

The project demonstrates practical applications of Object-Oriented Programming (OOP), Data Structures, Algorithms, and software testing.

## ✨ Features

### 1. Product Management
- Add new products.
- View all products.
- Search products by name.
- Delete products.
- Sort products by name, price, and quantity.

### 2. Inventory Management
- Restock existing products.
- Sell products.
- Validate stock quantities.
- Prevent overselling.
- Detect low-stock products using reorder thresholds.

### 3. Transaction Management
- Record restocking transactions.
- Record sales transactions.
- Generate unique transaction IDs.
- Store transaction timestamps.
- View transaction history by product ID.
- Filter transactions by date range.

### 4. Inventory Reports
- Total number of products.
- Total units available.
- Total inventory value.
- Number of low-stock products.

### 5. Validation and Error Handling
- Reject invalid product details.
- Prevent negative prices and quantities.
- Prevent invalid stock operations.
- Handle insufficient stock.
- Validate transaction and date-range inputs.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Core programming language |
| Maven | Project and dependency management |
| JUnit 5 | Unit testing |
| Git | Version control |
| GitHub | Source code hosting |
| IntelliJ IDEA | Development environment |

## 🧠 Data Structures and Algorithms

This project implements the following DSA concepts:

| Concept | Application |
|---|---|
| HashMap | Product storage and ID-based lookup |
| ArrayList | Transaction storage and result collections |
| Linear Search | Product and transaction filtering |
| Comparator | Product sorting |
| Collections Framework | Inventory data management |

### Time Complexity

| Operation | Average / Typical Complexity |
|---|---|
| Add product using HashMap | O(1) |
| Search product by ID | O(1) |
| Delete product by ID | O(1) |
| Search products by name | O(n) |
| Find low-stock products | O(n) |
| Filter transaction history | O(n) |
| Sort products | O(n log n) |

*HashMap operations have O(n) worst-case complexity in certain collision scenarios.*

## 🏗️ Project Architecture

The application follows a layered architecture.

```text
smart-inventory/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/inventory/
│   │           ├── Main.java
│   │           │
│   │           ├── model/
│   │           │   ├── Product.java
│   │           │   ├── StockTransaction.java
│   │           │   └── TransactionType.java
│   │           │
│   │           ├── repository/
│   │           │   ├── ProductRepository.java
│   │           │   └── TransactionRepository.java
│   │           │
│   │           └── service/
│   │               └── InventoryService.java
│   │
│   └── test/
│       └── java/
│           └── com/inventory/
│               ├── model/
│               ├── repository/
│               └── service/
│
├── pom.xml
├── README.md
└── run-inventory.bat
```

### Layer Responsibilities

- **Model:** Represents products and stock transactions.
- **Repository:** Manages product and transaction data.
- **Service:** Handles inventory business operations.
- **Main:** Provides the command-line interface.

## 🚀 Installation and Setup

### Prerequisites

Install the following:
- Java Development Kit (JDK) 21
- Apache Maven 3.9 or later
- IntelliJ IDEA or another Java IDE

### Step 1: Clone the repository

```bash
git clone https://github.com/Karan-033/Smart-Inventory.git
```

### Step 2: Navigate to the project

```bash
cd Smart-Inventory
```

### Step 3: Run the tests

```bash
mvn clean test
```

### Step 4: Launch the CLI application

```bash
mvn compile exec:java -Dexec.mainClass="com.inventory.Main"
```

### Step 5: Launch on Windows

Double-click:

```text
run-inventory.bat
```

This opens a terminal window and launches the Smart Inventory CLI application.

## 🖥️ Application Interface

The application provides a menu-driven command-line interface for performing inventory operations.

Example menu:

```text
========================================
       SMART INVENTORY MANAGEMENT
========================================

1. Add Product
2. View All Products
3. Search Product
4. Sort Products
5. Delete Product
6. Restock Product
7. Sell Product
8. View Low Stock Products
9. View Transaction History
10. Inventory Reports
0. Exit

Enter your choice:
```

*The displayed menu represents the intended full menu. Actual options depend on the current Main.java implementation.*

## 🧪 Testing

JUnit 5 is used to test the application's functionality.

Test coverage includes:
- Product creation and validation.
- Restocking and selling.
- Insufficient stock handling.
- Product repository operations.
- Transaction repository operations.
- Date-range filtering.
- Transaction recording.
- Unique transaction IDs.
- Inventory service operations.

Run all tests using:

```bash
mvn clean test
```

## ⚠️ Current Limitations

- Data is stored in memory using Java collections.
- Product and transaction data are lost when the application closes.
- The application currently uses a CLI rather than a graphical interface.
- Database persistence is not yet implemented.

## 🔮 Future Enhancements

- MySQL or SQLite database integration.
- Persistent product and transaction storage.
- User authentication and role management.
- Export inventory reports to CSV or PDF.
- Graphical desktop interface.
- Advanced sales analytics.

## 👨‍💻 Author

**Java DSA Learning Project**

GitHub Repository:  
https://github.com/Karan-033/Smart-Inventory

## 📄 License

This project is developed for educational and learning purposes.
