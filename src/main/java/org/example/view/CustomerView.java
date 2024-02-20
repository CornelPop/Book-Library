package org.example.view;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.CustomerController;
import org.example.model.Bill;
import org.example.model.Book;
import org.example.model.CustomerModel;
import org.example.model.builder.BookBuilder;
import org.example.service.book.BookServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class CustomerView {

    private CustomerModel customerModel;
    private Button buyButton;

    private Button logoutButton;
    private Button refreshButton;
    private TableView<Book> table;

    private TextField quantityTextField;

    public CustomerView(Stage customerStage, CustomerModel customerModel) {

        this.customerModel = customerModel;

        customerStage.setTitle("Book Store");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 900, 600);
        customerStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        customerStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("CARTURESTI");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 1, 1);
        gridPane.setBackground(Background.fill(Color.LIGHTGREEN));
    }

    private void initializeFields(GridPane gridPane) {
        buyButton = new Button("Buy");
        HBox buyButtonHBox = new HBox(10);
        buyButtonHBox.getChildren().add(buyButton);
        gridPane.add(buyButtonHBox, 0, 1);

        refreshButton = new Button("Refresh");
        HBox refreshButtonHBox = new HBox(10);
        refreshButtonHBox.getChildren().add(refreshButton);
        gridPane.add(refreshButtonHBox, 0, 4);

        logoutButton = new Button("Logout");
        HBox logoutButtonHBox = new HBox(10);
        logoutButtonHBox.getChildren().add(logoutButton);
        gridPane.add(logoutButtonHBox, 1, 4);

        TableColumn<Book, String> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(100);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(100);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, LocalDate> dateColumn = new TableColumn<>("Published Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Book, String> stockColumn = new TableColumn<>("Stock");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table = new TableView<>();
        table.setItems(getBooks());
        table.getColumns().addAll(idColumn, titleColumn, authorColumn, dateColumn, priceColumn, stockColumn);

        quantityTextField = new TextField();
        quantityTextField.setPromptText("Insert quantity (e.g. 7)");
        gridPane.add(quantityTextField, 0, 2);

        gridPane.add(table, 0, 3);
    }

    public Book bookSelected() {
        return table.getSelectionModel().getSelectedItem();
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener) {
        buyButton.setOnAction(buyButtonListener);
    }

    public void addRefreshButtonListener(EventHandler<ActionEvent> refreshButtonListener) {
        refreshButton.setOnAction(refreshButtonListener);
    }

    public void addLogoutButtonListener(EventHandler<ActionEvent> logoutButtonListener) {
        logoutButton.setOnAction(logoutButtonListener);
    }

    public ObservableList<Book> getBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();

        List<Book> allBooks = customerModel.getBooks();

        books.addAll(allBooks);

        return books;
    }

    public void emptyTableView() {
        getBooks().clear();
    }

    public TableView<Book> getTable() {
        return table;
    }

    public TextField getQuantityTextField() {
        return quantityTextField;
    }

    public void showErrorBox (String error) {
        Alert informationAlert = new Alert(Alert.AlertType.ERROR);
        informationAlert.setTitle("Error");
        informationAlert.setHeaderText("We encountered an error");
        informationAlert.setContentText(error);
        informationAlert.showAndWait();
    }


}
