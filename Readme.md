# Smart Inventory and Stock Management System

A Java-based inventory management application developed as part of my 50-project DSA learning series.
The goal of this project is to build a practical inventory management system while strengthening my understanding of Data Structures and Algorithms, Object-Oriented Programming, Java Collections, and software development practices.

## Project Overview

The Smart Inventory and Stock Management System is designed to manage products, monitor stock levels, handle inventory operations, and maintain product records.
The project is being developed incrementally, starting with a Java CLI application.

## Tech Stack

- **Language:** Java 21
- **Build Tool:** Apache Maven
- **Testing:** JUnit 5
- **Version Control:** Git and GitHub
- **IDE:** IntelliJ IDEA

## Planned Features

- Add, update, and delete products
- Search products by ID and name
- Display all available products
- Sort products by different attributes
- Monitor low-stock products
- Restock and sell products
- Manage customer orders
- Maintain inventory transaction history
- Save and load inventory data

## DSA Concepts

The following concepts will be implemented and explored throughout the project:

- HashMap and Hashing
- ArrayList and Collections
- Searching algorithms
- Sorting algorithms
- Queue
- Priority Queue
- Stack
- Time and Space Complexity

## Project Structure

```text
smart-inventory/
├── src/
│   ├── main/
│   │   └── java/com/inventory/
│   │       ├── cli/
│   │       ├── model/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── dsa/
│   │       ├── persistence/
│   │       └── exception/
│   └── test/
│       └── java/com/inventory/
├── pom.xml
├── .gitignore
└── README.md
```

## Setup Instructions

### Prerequisites

- JDK 21 or later
- Apache Maven
- Git

### Clone the repository

```bash
git clone https://github.com/Karan-033/Smart-Inventory.git
cd Smart-Inventory
```

### Build the project

```bash
mvn clean compile
```

### Run tests

```bash
mvn clean test
```

## Learning Progress

| Milestone | Description | Status |
|---|---|---|
| M1 | Project setup and Product domain model | Completed |
| M2 | Product Repository implementation | In Progress |
| M3–M12 | Inventory features, DSA, persistence and final testing | Planned |

## Development Approach

This project is being developed step by step.

Each feature includes:
- Understanding the requirement
- Implementing the functionality
- Applying relevant DSA concepts
- Writing unit tests
- Committing changes using Git
- Updating project documentation

## Author

Developed as part of my 50-project DSA learning journey.