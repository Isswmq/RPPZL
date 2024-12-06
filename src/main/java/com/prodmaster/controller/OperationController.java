package com.prodmaster.controller;

import com.prodmaster.entity.Component;
import com.prodmaster.entity.Operation;
import com.prodmaster.entity.Product;
import com.prodmaster.service.OperationService;
import com.prodmaster.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OperationController {

    private OperationService operationService;

    @FXML
    private TextField nameField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField searchIdField;

    @FXML
    private TableView<Operation> operationTable;
    @FXML
    private TableColumn<Operation, Integer> idColumn;
    @FXML
    private TableColumn<Operation, String> nameColumn;
    @FXML
    private TableColumn<Operation, Integer> durationColumn;

    private ObservableList<Operation> operationData;

    public OperationController() {
        this.operationService = new OperationService();
        operationData = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        loadOperations();
    }

    private void loadOperations() {
        List<Operation> operations = operationService.getAllOperations();
        operationTable.getItems().setAll(operations);
    }

    @FXML
    private void saveOperation() {
        Operation operation = new Operation();
        operation.setName(nameField.getText());
        operation.setDuration(Integer.parseInt(durationField.getText()));
        operationService.saveOperation(operation);
        loadOperations();
    }

    @FXML
    private void deleteOperation() {
        try {
            Integer id = Integer.parseInt(searchIdField.getText());
            operationService.deleteOperation(id);
            loadOperations();
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void searchOperationById() {
        try {
            Integer id = Integer.parseInt(searchIdField.getText());
            Operation operation = operationService.findOperationById(id);
            if (operation != null) {
                operationData.setAll(operation);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void updateOperation() {
        String idText = searchIdField.getText();
        String name = nameField.getText();
        int duration = Integer.parseInt(durationField.getText());

        if (idText.isEmpty() || name.isEmpty() || duration < 0) {
            System.out.println("Please fill out all fields.");
            return;
        }

        try {
            Integer id = Integer.parseInt(idText);
            Operation updatedOperation = operationService.updateOperation(id, name, duration);
            if (updatedOperation != null) {
                System.out.println("Operation updated successfully.");
                loadOperations();
            } else {
                System.out.println("Operation with this ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}
