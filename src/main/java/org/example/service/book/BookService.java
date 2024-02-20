package org.example.service.book;

import org.example.model.Bill;
import org.example.model.Book;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<Book> findAll();

    List<User> findAllUsers();

    Book findById(Long id);

    boolean save(Book book);

    boolean saveBill(Bill bill);

    int getAgeOfBook(Long id);

    boolean updateStock(Book book, int newStock);

    boolean updateBook(Book book, String newAuthor, String newTitle, LocalDate newPublishedDate, int newPrice, int newStock);

    boolean updateEmployee(User user, String username, String password);

    boolean updateBillBookId(Long billId, Long newBookId);

    boolean updateBillCustomerId(Long billId, Long newCustomerId);

    int getUserIdByUsername(String username);

    List<Bill> findAllBillsOfAnEmployee(Long customer_id);

    boolean deleteById(Book book, int id);

    boolean deleteEmployeeById(User user, Long id);


}
