package com;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final InventoryManager manager = new InventoryManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedInitialData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readIntInput("Enter your choice (1-6): ");

            switch (choice) {
                case 1 : handleAddBook();
                case 2 : handleViewAllBooks();
                case 3 : handleSearchBook();
                case 4 : handleUpdateBook();
                case 5 : handleDeleteBook();
                case 6 : {
                    System.out.println("\nExiting Library Inventory System. Goodbye!");
                    running = false;
                }
                default : System.out.println("Invalid choice! Please select an option between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=============================================");
        System.out.println("      LIBRARY INVENTORY MANAGEMENT SYSTEM    ");
        System.out.println("=============================================");
        System.out.println("1. Add New Book (Create)");
        System.out.println("2. Display All Books (Read)");
        System.out.println("3. Search Book by ID (Read)");
        System.out.println("4. Update Book Details (Update)");
        System.out.println("5. Remove Book (Delete)");
        System.out.println("6. Exit");
        System.out.println("=============================================");
    }

    private static void handleAddBook() {
        System.out.println("\n--- Add New Book ---");
        String id = readNonEmptyString("Enter Book ID: ");
        if (manager.getBookById(id) != null) {
            System.out.println("Error: A book with ID '" + id + "' already exists!");
            return;
        }
        String title = readNonEmptyString("Enter Title: ");
        String author = readNonEmptyString("Enter Author: ");

        if (manager.addBook(id, title, author)) {
            System.out.println("Success: Book added successfully.");
        } else {
            System.out.println("Error: Could not add book.");
        }
    }

    private static void handleViewAllBooks() {
        System.out.println("\n--- Library Inventory List ---");
        List<Book> books = manager.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books currently registered in the inventory.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void handleSearchBook() {
        System.out.println("\n--- Search Book ---");
        String id = readNonEmptyString("Enter Book ID to search: ");
        Book book = manager.getBookById(id);
        if (book != null) {
            System.out.println("Book Found:\n" + book);
        } else {
            System.out.println("Error: Book with ID '" + id + "' not found.");
        }
    }

    private static void handleUpdateBook() {
        System.out.println("\n--- Update Book ---");
        String id = readNonEmptyString("Enter Book ID to update: ");
        Book book = manager.getBookById(id);
        if (book == null) {
            System.out.println("Error: Book with ID '" + id + "' not found.");
            return;
        }

        System.out.println("Current details: " + book);
        System.out.print("Enter New Title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter New Author (leave blank to keep current): ");
        String newAuthor = scanner.nextLine();

        if (manager.updateBook(id, newTitle, newAuthor)) {
            System.out.println("Success: Book details updated.");
        } else {
            System.out.println("Error: Failed to update book details.");
        }
    }

    private static void handleDeleteBook() {
        System.out.println("\n--- Remove Book ---");
        String id = readNonEmptyString("Enter Book ID to remove: ");
        if (manager.deleteBook(id)) {
            System.out.println("Success: Book removed from inventory.");
        } else {
            System.out.println("Error: Book with ID '" + id + "' not found.");
        }
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static void seedInitialData() {
        manager.addBook("B001", "Clean Code", "Robert C. Martin");
        manager.addBook("B002", "Effective Java", "Joshua Bloch");
        manager.addBook("B003", "Design Patterns", "Erich Gamma");
    }
}