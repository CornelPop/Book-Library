package org.example.repository.book;

import org.example.model.Bill;
import org.example.model.Book;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator {

    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache) {
        super(bookRepository);
        this.cache = cache;
    }

    @Override
    public List<Book> findAll() {
        if (cache.hasResult()){
            return cache.load();
        }

        List<Book> books = decoratedRepository.findAll();

        cache.save(books);
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        if (cache.hasResult()){
            return cache.load()
                    .stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        Optional<Book> book = decoratedRepository.findById(id);
        return book;
    }

    @Override
    public boolean save(Book book) {

        cache.invalidateCache();
        return decoratedRepository.save(book);
    }

    @Override
    public boolean saveBill(Bill bill) {
        cache.invalidateCache();
        return decoratedRepository.saveBill(bill);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
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
    public boolean updateBook(Book book, String newAuthor, String newTitle, LocalDate newPublishedDate, int newPrice, int newStock) {
        return false;
    }

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
