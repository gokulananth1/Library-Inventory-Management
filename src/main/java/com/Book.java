package com;

import java.util.Objects;


public class Book {
    private final String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String id, String title, String author) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty.");
        }
        this.id = id.trim();
        this.title = (title != null && !title.trim().isEmpty()) ? title.trim() : "Untitled";
        this.author = (author != null && !author.trim().isEmpty()) ? author.trim() : "Unknown Author";
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        if (title != null && !title.trim().isEmpty()) this.title = title.trim(); 
    }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { 
        if (author != null && !author.trim().isEmpty()) this.author = author.trim(); 
    }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Book [ID: %s | Title: %s | Author: %s | Status: %s]",
                id, title, author, isAvailable ? "Available" : "Checked Out");
    }
}