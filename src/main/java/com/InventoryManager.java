package com;

import java.util.*;


public class InventoryManager {

    private static volatile InventoryManager instance;
    private final Map<String, Book> bookMap;

    public InventoryManager() {
        this.bookMap = new HashMap<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            synchronized (InventoryManager.class) {
                if (instance == null) {
                    instance = new InventoryManager();
                }
            }
        }
        return instance;
    }

    // Resets storage for unit test isolation
    public synchronized void clearInventory() {
        bookMap.clear();
    }

    // Fix for Bug 1: Rejects duplicate primary keys
    public synchronized boolean addBook(String id, String title, String author) {
        if (isInvalidString(id) || bookMap.containsKey(id.trim())) {
            return false;
        }
        Book newBook = new Book(id, title, author);
        bookMap.put(newBook.getId(), newBook);
        return true;
    }

    // Fix for Bug 2: Null/Empty string safety
    public Book getBookById(String id) {
        if (isInvalidString(id)) return null;
        return bookMap.get(id.trim());
    }

    public synchronized boolean updateBook(String id, String newTitle, String newAuthor) {
        if (isInvalidString(id) || !bookMap.containsKey(id.trim())) {
            return false;
        }
        Book book = bookMap.get(id.trim());
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        return true;
    }

    public synchronized boolean deleteBook(String id) {
        if (isInvalidString(id) || !bookMap.containsKey(id.trim())) {
            return false;
        }
        bookMap.remove(id.trim());
        return true;
    }

    // Fix for Bug 3: Defensive copy prevents external mutation
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    private boolean isInvalidString(String str) {
        return str == null || str.trim().isEmpty();
    }
}