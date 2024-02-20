package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.AdminModel;
import org.example.model.CustomerModel;
import org.example.model.EmployeeModel;
import org.example.model.User;
import org.example.model.validator.UserValidator;
import org.example.service.book.BookServiceImpl;
import org.example.service.user.AuthenticationService;
import org.example.view.AdminView;
import org.example.view.CustomerView;
import org.example.view.EmployeeView;
import org.example.view.LoginView;

import java.util.EventListener;
import java.util.List;

import static org.example.database.Constants.Roles.*;

public class LoginController {

    private EmployeeModel employeeModel;
    private CustomerModel customerModel;
    private AdminModel adminModel;
    private Stage customerStage;
    private Stage employeeStage;
    private Stage adminStage;
    private BookServiceImpl bookService;
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final UserValidator userValidator;
    private User user;


    public LoginController(LoginView loginView, AuthenticationService authenticationService, UserValidator userValidator, Stage customerStage, Stage employeeStage, Stage adminStage, CustomerModel customerModel, BookServiceImpl bookService, EmployeeModel employeeModel, AdminModel adminModel, User user) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.userValidator = userValidator;
        this.customerStage = customerStage;
        this.employeeStage = employeeStage;
        this.adminStage = adminStage;
        this.user = user;

        this.customerModel = customerModel;
        this.employeeModel = employeeModel;
        this.adminModel = adminModel;

        this.bookService = bookService;

        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            user = authenticationService.login(username, password);

            if (user == null){
                loginView.setActionTargetText("Invalid Username or password!");
            }else{
                if (user.getRoles().get(0).getRole().equals(CUSTOMER))
                {
                    System.out.println("merge CUSTOMER");
                    CustomerView customerView = new CustomerView(customerStage, customerModel);
                    new CustomerController(customerView, customerModel, bookService, authenticationService, customerStage);
                }

                if (user.getRoles().get(0).getRole().equals(EMPLOYEE))
                {
                    System.out.println("merge EMPLOYEE");
                    EmployeeView employeeView = new EmployeeView(employeeStage, employeeModel);
                    new EmployeeController(employeeView, employeeModel, bookService, authenticationService, user);
                }

                if (user.getRoles().get(0).getRole().equals(ADMINISTRATOR))
                {
                    System.out.println("merge ADMINISTRATOR");
                    AdminView adminView = new AdminView(adminStage, adminModel);
                    new AdminController(authenticationService, adminModel, adminView, bookService, adminStage, userValidator);
                }
            }
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            userValidator.validate(username, password);
            final List<String> errors = userValidator.getErrors();
            if (errors.isEmpty()) {
                if (authenticationService.register(username, password)){
                    loginView.setActionTargetText("Register successfull!");
                }else{
                    loginView.setActionTargetText("Register NOT successfull!");
                }
            } else {
                loginView.setActionTargetText(userValidator.getFormattedErrors());
            }
        }
    }

    public User getUser() {
        return user;
    }
}
