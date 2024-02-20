package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.controller.EmployeeController;
import org.example.controller.LoginController;
import org.example.database.DatabaseConnectionFactory;
import org.example.database.JDBConnectionWrapper;
import org.example.model.*;
import org.example.model.builder.BookBuilder;
import org.example.model.validator.UserValidator;
import org.example.repository.book.BookRepository;
import org.example.repository.book.BookRepositoryCacheDecorator;
import org.example.repository.book.BookRepositoryMySQL;
import org.example.repository.book.Cache;
import org.example.repository.security.RightsRolesRepository;
import org.example.repository.security.RightsRolesRepositoryMySQL;
import org.example.repository.user.UserRepository;
import org.example.repository.user.UserRepositoryMySQL;
import org.example.service.book.BookService;
import org.example.service.book.BookServiceImpl;
import org.example.service.user.AuthenticationService;
import org.example.service.user.AuthenticationServiceMySQL;
import org.example.view.CustomerView;
import org.example.view.EmployeeView;
import org.example.view.GeneratePdf;
import org.example.view.LoginView;

import java.sql.Connection;
import java.time.LocalDate;

import static javafx.application.Application.launch;
import static org.example.database.Constants.Schemas.PRODUCTION;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Connection connection = new JDBConnectionWrapper(PRODUCTION).getConnection();

        final RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        final UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        final AuthenticationService authenticationService = new AuthenticationServiceMySQL(userRepository,
                rightsRolesRepository);

        final LoginView loginView = new LoginView(primaryStage);

        final UserValidator userValidator = new UserValidator(userRepository);

        Stage customerStage = new Stage();
        Stage employeeStage= new Stage();
        Stage adminStage = new Stage();

        final BookRepositoryMySQL bookRepositoryMySQL = new BookRepositoryMySQL(connection);
        final BookServiceImpl bookService = new BookServiceImpl(bookRepositoryMySQL);

        /*bookRepositoryMySQL.save(new BookBuilder().setTitle("Submarinul de deasupra Londrei")
                .setAuthor("Cornel Pop")
                .setPublishedDate(LocalDate.of(2002, 12, 15))
                .setPrice(30)
                .setStock(50)
                .build());*/
        /*bookRepositoryMySQL.save(new BookBuilder().setTitle("Comoara din sertarul gol")
                .setAuthor("Razvan Pop")
                .setPublishedDate(LocalDate.of(2007, 5, 19))
                .setPrice(50)
                .setStock(30)
                .build());*/

        //bookRepositoryMySQL.removeAll();



        CustomerModel customerModel = new CustomerModel(bookService);
        EmployeeModel employeeModel = new EmployeeModel(bookService);
        AdminModel adminModel = new AdminModel(bookService);

        User user = new User();

        GeneratePdf generatePdf = new GeneratePdf(bookService);

        new LoginController(loginView, authenticationService, userValidator, customerStage, employeeStage, adminStage, customerModel, bookService, employeeModel, adminModel, user);
    }
}
