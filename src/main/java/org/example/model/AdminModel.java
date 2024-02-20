package org.example.model;

import org.example.service.book.BookServiceImpl;

import java.util.List;

public class AdminModel {
    private BookServiceImpl bookService;

    public AdminModel(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public List<User> getUsers() {
        return  bookService.findAllUsers();
    }
}
