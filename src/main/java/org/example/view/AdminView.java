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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.model.AdminModel;
import org.example.model.Bill;
import org.example.model.Book;
import org.example.model.User;

import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

public class AdminView {

    private AdminModel adminModel;
    private TableView<User> table;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Label responseLabel = new Label();
    private Label infoLabel = new Label("Press a customer and generate report");

    private Label usernameLabel = new Label("Username of the user");
    private Label passwordLabel = new Label("Password of the user");
    private Button addUserButton = new Button("Add employee");
    private Button updateUserButton = new Button("Update employee");
    private Button deleteUserButton = new Button("Delete employee");
    private Button generateReportOfSpecificUser = new Button("Report of specific employee");
    private Button refreshButton = new Button("Refresh");
    private Button showUsers = new Button("Show employees");
    private Button generateReportOfAllUsers = new Button("Report of all employees");
    private CheckBox enablePassCheckBox = new CheckBox("Enable password textField");

    public AdminView(Stage adminStage, AdminModel adminModel) {

        this.adminModel = adminModel;

        adminStage.setTitle("Book Store");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 1100, 700);
        adminStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        adminStage.show();
    }

    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane){
//        Text sceneTitle = new Text("Welcome to ADMIN page");
//        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
//        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane) {

        usernameTextField = new TextField();
        passwordTextField = new TextField();

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

        TableColumn<User, String> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(160);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(330);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));


        table = new TableView<>();
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                usernameTextField.setText(table.getSelectionModel().getSelectedItem().getUsername());
                passwordTextField.setText(table.getSelectionModel().getSelectedItem().getPassword());
            }
        });
        table.getColumns().addAll(idColumn, usernameColumn, passwordColumn);

        panel2.setCenter(table);
        initializePanel1(panel1, showUsers, generateReportOfAllUsers, refreshButton);
        initializePanel3(panel3, usernameTextField, passwordTextField, usernameLabel, passwordLabel, addUserButton, updateUserButton, deleteUserButton, generateReportOfSpecificUser, enablePassCheckBox, responseLabel, infoLabel);

    }

    public ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();

        List<User> allUsers = adminModel.getUsers();

        users.addAll(allUsers);

        return users;
    }

    private void initializePanel3(BorderPane panel3, TextField usernameTextField, TextField passwordTextField,
                                  Label usernameLabel, Label passwordLabel, Button addUserButton,
                                  Button updateUserButton, Button deleteUserButton, Button generateReportOfSpecificUser,
                                  CheckBox enablePassCheckBox, Label responseLabel, Label infoLabel){

        addUserButton.setPrefSize(130,40);
        updateUserButton.setPrefSize(130,40);
        deleteUserButton.setPrefSize(130,40);
        generateReportOfSpecificUser.setPrefSize(175,40);

        passwordTextField.setDisable(true);

        VBox elementsContainer3 = new VBox();
        VBox container = new VBox(5);
        container.getChildren().addAll(new javafx.scene.control.Label(), new javafx.scene.control.Label());
        VBox container2 = new VBox(5);
        container2.getChildren().addAll(new javafx.scene.control.Label(), new javafx.scene.control.Label());
        elementsContainer3.setSpacing(5);
        elementsContainer3.setPadding(new Insets(5, 5, 5, 5));
        elementsContainer3.getChildren().addAll(usernameLabel,
                usernameTextField,
                passwordLabel,
                passwordTextField,
                enablePassCheckBox,
                container,
                addUserButton,
                updateUserButton,
                deleteUserButton,
                responseLabel,
                container2,
                infoLabel,
                generateReportOfSpecificUser);

        elementsContainer3.setAlignment(Pos.CENTER);

        panel3.setMinSize(72, 690);
        panel3.setCenter(elementsContainer3);
    }

    private void initializePanel1(BorderPane panel1, Button showUsersButton, Button generateReportOfAllUsers, Button refreshButton)
    {
        showUsersButton.setPrefSize(130, 40);
        refreshButton.setPrefSize(130,40);
        generateReportOfAllUsers.setPrefSize(175, 40);

        VBox elementsContainer1 = new VBox();
        elementsContainer1.setSpacing(10);
        elementsContainer1.setPadding(new Insets(5, 5, 5, 5));
        elementsContainer1.getChildren().addAll(showUsersButton, refreshButton, generateReportOfAllUsers);
        elementsContainer1.setAlignment(Pos.CENTER);

        panel1.setCenter(elementsContainer1);
    }

    public void showUsersButtonListener(EventHandler<ActionEvent> showUsersButtonListener) {
        showUsers.setOnAction(showUsersButtonListener);
    }

    public void generateAllReportsButtonListener(EventHandler<ActionEvent> generateAllReportsButtonListener) {
        generateReportOfAllUsers.setOnAction(generateAllReportsButtonListener);
    }

    public void addAddUserButtonListener(EventHandler<ActionEvent> addUserButtonListener) {
        addUserButton.setOnAction(addUserButtonListener);
    }

    public void addUpdateUserButtonListener(EventHandler<ActionEvent> updateUserButtonListener) {
        updateUserButton.setOnAction(updateUserButtonListener);
    }

    public void addDeleteUserButtonListener(EventHandler<ActionEvent> deleteUserButtonListener) {
        deleteUserButton.setOnAction(deleteUserButtonListener);
    }

    public void addGenerateReportButtonListener(EventHandler<ActionEvent> generateReportButtonListener) {
        generateReportOfSpecificUser.setOnAction(generateReportButtonListener);
    }

    public void addRefreshButtonListener(EventHandler<ActionEvent> showBooksButtonListener) {
        refreshButton.setOnAction(showBooksButtonListener);
    }

    public void addEnablePassCheckBoxListener(EventHandler<ActionEvent> enablePassCheckBoxListener) {
        enablePassCheckBox.setOnAction(enablePassCheckBoxListener);
    }

    public TableView<User> getTable() {
        return table;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public CheckBox getEnablePassCheckBox() {
        return enablePassCheckBox;
    }

    public Label getResponseLabel() {
        return responseLabel;
    }
}
