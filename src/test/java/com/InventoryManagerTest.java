package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {

    private InventoryManager manager;

    @BeforeEach
    void setUp() {
        manager = InventoryManager.getInstance();
        manager.clearInventory();
    }

    @Test
    @DisplayName("Should successfully add a valid book")
    void testAddBookSuccess() {
        boolean added = manager.addBook("B001", "Clean Code", "Robert Martin");
        assertTrue(added, "Adding a new book should return true");
        assertNotNull(manager.getBookById("B001"));
    }

    @Test
    @DisplayName("Should reject duplicate book ID")
    void testAddDuplicateBookId() {
        manager.addBook("B001", "Clean Code", "Robert Martin");
        boolean addedDuplicate = manager.addBook("B001", "Refactoring", "Martin Fowler");
        assertFalse(addedDuplicate, "Adding duplicate ID must return false");
    }

    @Test
    @DisplayName("Should retrieve book by correct ID")
    void testGetBookByIdFound() {
        manager.addBook("B002", "Effective Java", "Joshua Bloch");
        Book book = manager.getBookById("B002");
        assertNotNull(book);
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    @DisplayName("Should return null for non-existent book ID")
    void testGetBookByIdNotFound() {
        Book book = manager.getBookById("INVALID_ID");
        assertNull(book);
    }

    @Test
    @DisplayName("Should return defensive copy of all books")
    void testGetAllBooks() {
        manager.addBook("B001", "Book One", "Author A");
        manager.addBook("B002", "Book Two", "Author B");
        List<Book> books = manager.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    @DisplayName("Should update existing book details")
    void testUpdateBookSuccess() {
        manager.addBook("B003", "Old Title", "Old Author");
        boolean updated = manager.updateBook("B003", "New Title", "New Author");
        assertTrue(updated);
        Book book = manager.getBookById("B003");
        assertEquals("New Title", book.getTitle());
    }

    @Test
    @DisplayName("Should return false when updating missing book")
    void testUpdateBookNotFound() {
        boolean updated = manager.updateBook("NON_EXISTENT", "Title", "Author");
        assertFalse(updated);
    }

    @Test
    @DisplayName("Should delete book successfully")
    void testDeleteBookSuccess() {
        manager.addBook("B004", "To Delete", "Author C");
        boolean deleted = manager.deleteBook("B004");
        assertTrue(deleted);
        assertNull(manager.getBookById("B004"));
    }
}