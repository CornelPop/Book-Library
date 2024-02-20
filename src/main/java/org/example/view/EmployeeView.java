package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.model.Bill;
import org.example.model.Book;
import org.example.model.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class EmployeeView {

    private EmployeeModel employeeModel;
    private Button addSellToEmployeeButton;
    private TableView<Book> table;
    private TableView<Bill> table2;
    private TextField titleTextField;
    private TextField authorTextField;
    private TextField publishedDateTextField;
    private TextField priceTxetField;
    private TextField stockTextField;
    private Label titleLabel = new Label("Title of the book");
    private Label infoLabel = new Label("Press a bill and add to inventory");
    private Label authorLabel = new Label("Author of the book");
    private Label publisedDateLabel = new Label("Published Date of the book YYYY-M-D");
    private Label stockLabel = new Label("How many books left");
    private Label priceLabel = new Label("Price of the book");
    private Button showBooksButton = new Button("Show books");
    private Button showBillsButton = new Button("Show bills");
    private Button refreshButton = new Button("Refresh");
    private Button reportOfAllBooksButton = new Button("Report of all books");
    private Button addBookButton = new Button("Add book");
    private Button updateBookButton = new Button("Update book");
    private Button deleteBookButton = new Button("Delete book");
    private Button addToInventory = new Button("Add to inventory");

    public EmployeeView(Stage employeeStage, EmployeeModel employeeModel) {

        this.employeeModel = employeeModel;

        employeeStage.setTitle("Book Store");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 1100, 700);
        employeeStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        employeeStage.show();
    }

    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setBackground(Background.fill(Color.LIGHTGREEN));
    }

    private void initializeSceneTitle(GridPane gridPane){
        /*Text sceneTitle = new Text("Welcome to EMPLOYEE page");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);*/
    }

    private void initializeFields(GridPane gridPane) {

        titleTextField = new TextField();
        authorTextField = new TextField();
        publishedDateTextField = new TextField();
        priceTxetField = new TextField();
        stockTextField = new TextField();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);

        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        BorderPane panel1 = new BorderPane();
        panel1.setStyle("-fx-background-color: lightgreen;");
        panel1.setBorder(new Border(new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2) )));

        BorderPane panel2 = new BorderPane();
        panel2.setStyle("-fx-background-color: lightgreen;");
        panel2.setBorder(new Border(new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2) )));

        BorderPane panel3 = new BorderPane();
        panel3.setStyle("-fx-background-color: lightgreen;");
        panel3.setBorder(new Border(new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2) )));
        gridPane.add(panel1, 0, 0);
        gridPane.add(panel2, 1, 0);
        gridPane.add(panel3, 2, 0);

        initializePanel3(panel3, titleTextField, authorTextField, publishedDateTextField, stockTextField, priceTxetField, authorLabel, titleLabel, publisedDateLabel, priceLabel, stockLabel, addBookButton, updateBookButton, deleteBookButton, addToInventory, infoLabel);
        initializePanel1(panel1, showBooksButton, showBillsButton, refreshButton, reportOfAllBooksButton);

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

        TableColumn<Bill, String> idColumn2 = new TableColumn<>("Id");
        idColumn2.setMinWidth(100);
        idColumn2.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Bill, String> bookIdColumn = new TableColumn<>("Book Id");
        bookIdColumn.setMinWidth(100);
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("book_id"));

        TableColumn<Bill, String> customerIdColumn = new TableColumn<>("Employee Id");
        customerIdColumn.setMinWidth(100);
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

        TableColumn<Bill, LocalDate> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Bill, String> amountPaidColumn = new TableColumn<>("Amount Paid");
        amountPaidColumn.setMinWidth(100);
        amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));


        table = new TableView<>();
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                titleTextField.setText(table.getSelectionModel().getSelectedItem().getTitle());
                authorTextField.setText(table.getSelectionModel().getSelectedItem().getAuthor());
                publishedDateTextField.setText(table.getSelectionModel().getSelectedItem().getPublishedDate().toString());
                stockTextField.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getStock()));
                priceTxetField.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getPrice()));
            }
        });
        table.getColumns().addAll(idColumn, titleColumn, authorColumn, dateColumn, priceColumn, stockColumn);

        table2 = new TableView<>();
        table2.getColumns().addAll(idColumn2, bookIdColumn, customerIdColumn, quantityColumn, amountPaidColumn);

        table.setMaxHeight(330);
        table2.setMaxHeight(330);
        panel2.setTop(table);
        panel2.setBottom(table2);
    }

    public ObservableList<Book> getBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();

        List<Book> allBooks = employeeModel.getBooks();

        books.addAll(allBooks);

        return books;
    }

    public ObservableList<Bill> getBills() {
        ObservableList<Bill> bills = FXCollections.observableArrayList();

        List<Bill> allBills = employeeModel.getBills();

        for (Bill bill : allBills) {
            bill.setCustomer_id(0);
            bills.add(bill);
        }

        //bills.addAll(allBills);
        return bills;
    }

    private void initializePanel3(BorderPane panel3, TextField titleTextField, TextField authorTextField, TextField publishedDateTextField,
                                  TextField priceTxetField, TextField stockTextField, Label authorLabel, Label titleLabel,
                                  Label publishedDateLabel, Label stockLabel, Label priceLabel, Button addBookButton,
                                  Button updateBookButton, Button deleteBookButton, Button addToInventory, Label infoLabel)
    {
        addBookButton.setPrefSize(130, 40);
        updateBookButton.setPrefSize(130, 40);
        deleteBookButton.setPrefSize(130, 40);
        addToInventory.setPrefSize(130, 40);

        VBox elementsContainer3 = new VBox();
        VBox container = new VBox(5);
        container.getChildren().addAll(new Label(), new Label());
        VBox container2 = new VBox(5);
        container2.getChildren().addAll(new Label(), new Label());
        elementsContainer3.setSpacing(5);
        elementsContainer3.setPadding(new Insets(5, 5, 5, 5));
        elementsContainer3.getChildren().addAll(titleLabel,
                titleTextField,
                authorLabel,
                authorTextField,
                publishedDateLabel,
                publishedDateTextField,
                priceLabel,
                priceTxetField,
                stockLabel,
                stockTextField,
                container,
                addBookButton,
                updateBookButton,
                deleteBookButton,
                container2,
                infoLabel,
                addToInventory);
        elementsContainer3.setAlignment(Pos.CENTER);

        panel3.setMinSize(72, 690);
        panel3.setCenter(elementsContainer3);
    }

    private void initializePanel1(BorderPane panel1, Button showBooksButton, Button showBills, Button refreshButton, Button reportOfAllBooksButton)
    {
        showBooksButton.setPrefSize(130, 40);
        showBillsButton.setPrefSize(130, 40);
        refreshButton.setPrefSize(130, 40);
        reportOfAllBooksButton.setPrefSize(130, 40);

        VBox elementsContainer1 = new VBox();
        elementsContainer1.setSpacing(10);
        elementsContainer1.setPadding(new Insets(5, 5, 5, 5));
        elementsContainer1.getChildren().addAll(showBooksButton, showBills, refreshButton, reportOfAllBooksButton);
        elementsContainer1.setAlignment(Pos.CENTER);

        panel1.setCenter(elementsContainer1);
    }

    public TableView<Book> getTable() {
        return table;
    }
    public TableView<Bill> getTable2() {
        return table2;
    }

    public void addRefreshButtonListener(EventHandler<ActionEvent> showBooksButtonListener) {
        refreshButton.setOnAction(showBooksButtonListener);
    }

    public void addShowBooksButtonListener(EventHandler<ActionEvent> showBooksButtonListener) {
        showBooksButton.setOnAction(showBooksButtonListener);
    }

    public void addShowBillsButtonListener(EventHandler<ActionEvent> showBillsButtonListener) {
        showBillsButton.setOnAction(showBillsButtonListener);
    }

    public void addBookButtonListener(EventHandler<ActionEvent> addBookButtonListener) {
        addBookButton.setOnAction(addBookButtonListener);
    }

    public void addDeleteBookButtonListener(EventHandler<ActionEvent> deleteBookButtonListener) {
        deleteBookButton.setOnAction(deleteBookButtonListener);
    }

    public void addUpdateBookButtonListener(EventHandler<ActionEvent> updateBookButtonListener) {
        updateBookButton.setOnAction(updateBookButtonListener);
    }

    public void addReportOfAllBooksButtonListener(EventHandler<ActionEvent> reportOfAllBooksButtonListener) {
        reportOfAllBooksButton.setOnAction(reportOfAllBooksButtonListener);
    }

    public void addToInventoryButtonListener(EventHandler<ActionEvent> addToInventoryButtonListener) {
        addToInventory.setOnAction(addToInventoryButtonListener);
    }

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public TextField getAuthorTextField() {
        return authorTextField;
    }

    public TextField getPublishedDateTextField() {
        return publishedDateTextField;
    }

    public TextField getPriceTxetField() {
        return priceTxetField;
    }

    public TextField getStockTextField() {
        return stockTextField;
    }

    public Book bookSelected() {
        return table.getSelectionModel().getSelectedItem();
    }
    public Bill billSelected() {
        return table2.getSelectionModel().getSelectedItem();
    }

}
