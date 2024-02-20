package org.example.model;

import org.example.service.book.BookServiceImpl;

import java.util.List;

public class EmployeeModel {

    private BookServiceImpl bookService;

    public EmployeeModel(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public List<Book> getBooks() {
        return  bookService.findAll();
    }

    public List<Bill> getBills() {
        return  bookService.findAllBills();
    }

}
