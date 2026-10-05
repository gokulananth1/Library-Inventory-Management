# Library Inventory Management System

A modular Command Line Interface (CLI) application developed in Java to manage a library's book inventory. Built with object-oriented principles, this project manages book records in-memory using Java Collections and uses Maven for project build management.

---

## Features

- **Create (Add Book):** Register new books with unique IDs, titles, and author details.
- **Read (Display & Search):** View the full list of books or search for a specific book by its unique ID.
- **Update (Modify Details):** Edit existing book metadata (Title and Author).
- **Delete (Remove Book):** Remove catalog items from the system using their ID.
- **In-Memory Storage:** Utilizes Java `HashMap` for fast $O(1)$ lookups and `ArrayList` for displaying records.
- **Input Validation & Exception Handling:** Includes error handling for invalid user inputs, non-numeric choices, and empty string fields.

---

## Technical Stack

- **Language:** Java (JDK 8 or higher)
- **Build Tool:** Maven (`pom.xml`)
- **Data Structures:** `HashMap<String, Book>` for key-value retrieval, `ArrayList<Book>` for collections.
- **IDE:** Eclipse IDE
- **Version Control:** Git & GitHub

---

## Project Structure

```text
Library_Inventory_Management/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               ├── Book.java
│               ├── InventoryManager.java
│               └── Main.java
├── pom.xml
└── README.md
