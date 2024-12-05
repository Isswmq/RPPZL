package com.prodmaster.controller;

import com.prodmaster.entity.Component;
import com.prodmaster.entity.Operation;
import com.prodmaster.entity.Product;
import com.prodmaster.service.OperationService;
import com.prodmaster.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class OperationController {

    private OperationService operationService;

    @FXML
    private TextField nameField;

    @FXML
    private TextField durationField;

    @FXML
    private TableView<Operation> operationTable;
    @FXML
    private TableColumn<Operation, Integer> idColumn;
    @FXML
    private TableColumn<Operation, String> nameColumn;
    @FXML
    private TableColumn<Operation, Integer> durationColumn;

    public OperationController() {
        this.operationService = new OperationService();
    }

    @FXML
    public void initialize() {
        // Настройка столбцов таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        loadComponents();
    }

    private void loadComponents() {
        List<Operation> operations = operationService.getAllOperations();
        operationTable.getItems().setAll(operations);
    }

    @FXML
    private void saveOperation() {
        Operation operation = new Operation();
        operation.setName(nameField.getText());
        operation.setDuration(Integer.parseInt(durationField.getText()));
        operationService.saveOperation(operation);
        loadComponents();
    }

    @FXML
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}
