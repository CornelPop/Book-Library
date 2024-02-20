package org.example.controller;

import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.model.*;
import org.example.model.builder.BookBuilder;
import org.example.service.book.BookServiceImpl;
import org.example.service.user.AuthenticationService;
import org.example.view.EmployeeView;
import org.example.view.GeneratePdf;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeController {

    private AuthenticationService authenticationService;
    private EmployeeModel employeeModel;
    private EmployeeView employeeView;
    private BookServiceImpl bookService;
    private GeneratePdf generatePdf;
    private User user;

    public EmployeeController(EmployeeView employeeView, EmployeeModel employeeModel, BookServiceImpl bookService, AuthenticationService authenticationService, User user) {
        this.authenticationService = authenticationService;
        this.employeeModel = employeeModel;
        this.employeeView = employeeView;
        this.bookService = bookService;
        this.user = user;
        generatePdf = new GeneratePdf(bookService);

        this.employeeView.addRefreshButtonListener(new refreshButtonListener());
        this.employeeView.addShowBooksButtonListener(new showBooksButtonListener());
        this.employeeView.addBookButtonListener(new addBookButtonListener());
        this.employeeView.addDeleteBookButtonListener(new deleteBookButtonListener());
        this.employeeView.addUpdateBookButtonListener(new updateBookButtonListener());
        this.employeeView.addShowBillsButtonListener(new showBillsButtonListener());
        this.employeeView.addReportOfAllBooksButtonListener(new reportOfAllBooksButtonListener());
        this.employeeView.addToInventoryButtonListener(new addToInventoryButtonListener());
    }

    private class showBooksButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.getTable().setItems(employeeView.getBooks());
        }
    }
    private class showBillsButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.getTable2().setItems(employeeView.getBills());
        }
    }

    private class reportOfAllBooksButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            try {
                Long id = (long) bookService.getUserIdByUsername(user.getUsername());
                generatePdf.createPdfForOneEmployee(bookService.findAllBillsOfAnEmployee(id));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private class addToInventoryButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            Long id = (long) bookService.getUserIdByUsername(user.getUsername());
            bookService.updateBillCustomerId(employeeView.getTable2().getSelectionModel().getSelectedItem().getId(), id);
        }

    }
    private class refreshButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.getTable().refresh();
            employeeView.getTable().setItems(employeeView.getBooks());

            employeeView.getTable2().refresh();
            employeeView.getTable2().setItems(employeeView.getBills());
        }
    }
    private class addBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {

            String inputText = employeeView.getPublishedDateTextField().getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate parsedDate = LocalDate.parse(inputText, formatter);

            bookService.save(new BookBuilder().setTitle(employeeView.getTitleTextField().getText())
                    .setAuthor(employeeView.getAuthorTextField().getText())
                    .setPublishedDate(parsedDate)
                    .setPrice(Integer.parseInt(employeeView.getPriceTxetField().getText()))
                    .setStock(Integer.parseInt(employeeView.getStockTextField().getText()))
                    .build());

            employeeView.getTitleTextField().setText("");
            employeeView.getAuthorTextField().setText("");
            employeeView.getPublishedDateTextField().setText("");
            employeeView.getPriceTxetField().setText("");
            employeeView.getStockTextField().setText("");
        }
    }
    private class deleteBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {

            bookService.deleteById(employeeView.bookSelected(), employeeView.bookSelected().getId().intValue());

            employeeView.getTitleTextField().setText("");
            employeeView.getAuthorTextField().setText("");
            employeeView.getPublishedDateTextField().setText("");
            employeeView.getPriceTxetField().setText("");
            employeeView.getStockTextField().setText("");
        }
    }
    private class updateBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {

            String inputText = employeeView.getPublishedDateTextField().getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate parsedDate = LocalDate.parse(inputText, formatter);

            bookService.updateBook(employeeView.bookSelected(),
                    employeeView.getAuthorTextField().getText(),
                    employeeView.getTitleTextField().getText(),
                    parsedDate,
                    Integer.parseInt(employeeView.getPriceTxetField().getText()),
                    Integer.parseInt(employeeView.getStockTextField().getText()));

            employeeView.getTitleTextField().setText("");
            employeeView.getAuthorTextField().setText("");
            employeeView.getPublishedDateTextField().setText("");
            employeeView.getPriceTxetField().setText("");
            employeeView.getStockTextField().setText("");
        }
    }
}
