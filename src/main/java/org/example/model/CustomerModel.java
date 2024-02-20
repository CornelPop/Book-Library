package org.example.model;

import org.example.service.book.BookServiceImpl;

import java.util.List;

public class CustomerModel {

    private BookServiceImpl bookService;

    public CustomerModel(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public List<Book> getBooks() {
        return  bookService.findAll();
    }
}
