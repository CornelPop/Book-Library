package org.example.repository;

import org.example.database.DatabaseConnectionFactory;
import org.example.model.Book;
import org.example.model.builder.BookBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryMySQLTest {
    /*BookRepository bookRepository;
    @BeforeEach
    public void reinit() {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory();
        bookRepository = new BookRepositoryMySQL((Connection) databaseConnectionFactory.getConnectionWrapper(true));

        String sql = "DROP DATABASE IF EXISTS test_library;" +
                "CREATE DATABASE test_library;" +
                "USE test_library;" +
                "CREATE TABLE book" +
                " (id BIGINT auto_increment not null unique primary key," +
                "author varchar(50) not null," +
                "title varchar(50) not null, " +
                "publishedDate Date unique not null);" +
                "INSERT INTO book VALUES(null, 'Ala Bala', 'Portocala','2022-2-2' );" +
                "INSERT INTO book VALUES(null, 'Maria', 'Galbeneaua', '2022-3-3');" +
                "INSERT INTO book VALUES(null, 'Codrin', 'A little ship', '2022-4-4');";
        try {
            Statement statement =  ((Connection)databaseConnectionFactory.getConnectionWrapper(true)).createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Arguments> findAllArg() {
        List<Arguments> a = new ArrayList<>();
        List<Book> expected = new ArrayList<>();
        expected.add(new BookBuilder().setAuthor("Ala Bala").setTitle("Portocala").setPublishedDate(LocalDate.of(2022, 2, 2)).build());
        expected.add(new BookBuilder().setAuthor("Maria").setTitle("Galbeneaua").setPublishedDate(LocalDate.of(2022, 3, 3)).build());
        expected.add(new BookBuilder().setAuthor("Codrin").setTitle("A little ship").setPublishedDate(LocalDate.of(2022, 4, 4)).build());
        a.add(Arguments.of(expected));
        return a;
    }
    private static List<Arguments> removeAllArg() {
        List<Arguments> a = new ArrayList<>();
        List<Book> expected = new ArrayList<>();
        a.add(Arguments.of());
        return a;
    }

    @ParameterizedTest
    @MethodSource("removeAllArg")
    public void test_findAll(List<Book> expected) {
        Assertions.assertArrayEquals(expected.toArray(), bookRepository.findAll().toArray());
    }
    @ParameterizedTest
    @MethodSource("findAllArg")
    public void test_removeall() {
        bookRepository.removeAll();
        Assertions.assertArrayEquals(new ArrayList<Book>().toArray(), bookRepository.findAll().toArray());
    }
    @ParameterizedTest
    @MethodSource("findAllArg")
    public void test_findAllMock(List<Book> expected) {
        bookRepository.findAll();
        Assertions.assertArrayEquals(expected.toArray(), bookRepository.findAll().toArray());
    }
    @ParameterizedTest
    @MethodSource("findAllArg")
    public void test_removeallMock() {
        bookRepository.removeAll();
        Assertions.assertArrayEquals(new ArrayList<Book>().toArray(), bookRepository.findAll().toArray());
    }

*/

}