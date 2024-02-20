package org.example.repository.book;

import org.example.model.Bill;
import org.example.model.Book;
import org.example.model.User;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository{
    private List<Book> books;
    private List<Bill> bills;
    private final Connection connection;

    public BookRepositoryMock(Connection connection){
        this.connection = connection;
        books = new ArrayList<>();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return null;
    }

    /*@Override
    public Optional<Book> findById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }*/

    @Override
    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public boolean saveBill(Bill bill) {
        return bills.add(bill);
    }

    @Override
    public void removeAll() {
        books.clear();
    }

    @Override
    public boolean updateBillBookId(Long billId, Long newBookId) {
        return false;
    }

    @Override
    public int getUserIdByUsername(String username) {
        return 0;
    }

    @Override
    public List<Bill> findAllBillsOfAnEmployee(Long customer_id) {
        return null;
    }

    @Override
    public boolean updateBillCustomerId(Long billId, Long newCustomerId) {
        return false;
    }

    @Override
    public boolean updateStock(Book book, int newStock) {
        return false;
    }

    @Override
    public boolean updateEmployee(User user, String username, String password) {
        return false;
    }

    @Override
    public boolean updateBook(Book book, String newAuthor, String newTitle, LocalDate newPublishedDate, int newPrice, int newStock){return false;};

    @Override
    public boolean deleteById(Book book, int id) {
        return false;
    }

    @Override
    public boolean deleteEmployeeById(User user, Long id) {
        return false;
    }

    @Override
    public List<Bill> findAllBills() {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
