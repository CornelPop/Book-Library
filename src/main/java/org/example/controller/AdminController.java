package org.example.controller;

import com.itextpdf.text.DocumentException;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.example.model.AdminModel;
import org.example.model.validator.UserValidator;
import org.example.service.book.BookServiceImpl;
import org.example.service.user.AuthenticationService;
import org.example.service.user.AuthenticationServiceMySQL;
import org.example.view.AdminView;
import org.example.view.GeneratePdf;

import javax.swing.event.ChangeListener;
import java.io.FileNotFoundException;
import java.util.List;

public class AdminController {

    private AuthenticationService authenticationService;
    private AdminModel adminModel;
    private AdminView adminView;
    private BookServiceImpl bookService;
    private Stage adminStage;
    private UserValidator userValidator;
    private GeneratePdf generatePdf;

    public AdminController(AuthenticationService authenticationService, AdminModel adminModel, AdminView adminView, BookServiceImpl bookService, Stage adminStage, UserValidator userValidator) {
        this.authenticationService = authenticationService;
        this.adminModel = adminModel;
        this.adminView = adminView;
        this.bookService = bookService;
        this.adminStage = adminStage;
        this.userValidator = userValidator;
        generatePdf = new GeneratePdf(bookService);

        this.adminView.addGenerateReportButtonListener(new generateReportButtonListener());
        this.adminView.addAddUserButtonListener(new addUserButtonListener());
        this.adminView.addUpdateUserButtonListener(new updateUserButtonListener());
        this.adminView.addDeleteUserButtonListener(new deleteUserButtonListener());
        this.adminView.generateAllReportsButtonListener(new GenerateAllReportsButtonListener());
        this.adminView.showUsersButtonListener(new ShowUsersButtonListener());
        this.adminView.addEnablePassCheckBoxListener(new enablePassCheckBoxListener());
        this.adminView.addRefreshButtonListener(new refreshButtonListener());
    }

    private class GenerateAllReportsButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {
                generatePdf.createPdfForAllEmployees(bookService.findAllUsers());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ShowUsersButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            adminView.getTable().setItems(adminView.getUsers());
            adminView.getPasswordTextField().setDisable(true);
            adminView.getEnablePassCheckBox().setSelected(false);
        }
    }

    private class addUserButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = adminView.getUsernameTextField().getText();
            String password = adminView.getPasswordTextField().getText();

            userValidator.validate(username, password);
            final List<String> errors = userValidator.getErrors();
            if (errors.isEmpty()) {
                if (authenticationService.registerEmployee(username, password)){
                    adminView.getResponseLabel().setText("User added successfully!");
                }else{
                    adminView.getResponseLabel().setText("User not added successfully!");
                }
            } else {
                adminView.getResponseLabel().setText(userValidator.getFormattedErrors());
            }
        }
    }

    private class updateUserButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = adminView.getUsernameTextField().getText();
            String password = adminView.getPasswordTextField().getText();

            String encodedPassword = authenticationService.hashPassword(password);

            userValidator.validate(username, password);
            final List<String> errors = userValidator.getErrors();
            if (errors.isEmpty()) {
                if (bookService.updateEmployee(adminView.getTable().getSelectionModel().getSelectedItem(), username, encodedPassword)){
                    adminView.getResponseLabel().setText("User updated successfully!");
                }else{
                    adminView.getResponseLabel().setText("User not updated successfully!");
                }
            } else {
                adminView.getResponseLabel().setText(userValidator.getFormattedErrors());
            }        }
    }

    private class deleteUserButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            boolean result = bookService.deleteEmployeeById(adminView.getTable().getSelectionModel().getSelectedItem(), adminView.getTable().getSelectionModel().getSelectedItem().getId());
            if (result)
            {
                adminView.getResponseLabel().setText("User deleted successfully!");
            }
            else
            {
                adminView.getResponseLabel().setText("User not deleted successfully!");
            }
        }
    }

    private class generateReportButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {
                generatePdf.createPdfForOneEmployee(bookService.findAllBillsOfAnEmployee(adminView.getTable().getSelectionModel().getSelectedItem().getId()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class refreshButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            adminView.getTable().refresh();
            adminView.getTable().setItems(adminView.getUsers());
            adminView.getResponseLabel().setText("");
        }
    }

    private class enablePassCheckBoxListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (adminView.getEnablePassCheckBox().isSelected())
            {
                adminView.getPasswordTextField().setDisable(false);
            }
            else
            {
                adminView.getPasswordTextField().setDisable(true);
            }
        }
    }

}
