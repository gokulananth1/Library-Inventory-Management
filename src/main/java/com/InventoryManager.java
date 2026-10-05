package com;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InventoryManager {
   
    private final Map<String, Book> bookMap = new HashMap<>();

    public boolean addBook(String id, String title, String author) {
        if (bookMap.containsKey(id)) {
            return false; // Prevent duplicate IDs
        }
        bookMap.put(id, new Book(id, title, author));
        return true;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    public Book getBookById(String id) {
        return bookMap.get(id);
    }

    public boolean updateBook(String id, String newTitle, String newAuthor) {
        Book book = bookMap.get(id);
        if (book == null) {
            return false;
        }
        if (!newTitle.trim().isEmpty()) {
            book.setTitle(newTitle);
        }
        if (!newAuthor.trim().isEmpty()) {
            book.setAuthor(newAuthor);
        }
        return true;
    }

    public boolean deleteBook(String id) {
        return bookMap.remove(id) != null;
    }
}