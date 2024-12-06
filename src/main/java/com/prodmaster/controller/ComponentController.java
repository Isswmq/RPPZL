package com.prodmaster.controller;

import com.prodmaster.entity.Component;
import com.prodmaster.service.ComponentService;
import com.prodmaster.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ComponentController {

    private final ComponentService componentService;

    @FXML
    private TextField nameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField searchIdField;
    @FXML
    private TableView<Component> componentTable;
    @FXML
    private TableColumn<Component, Integer> idColumn;
    @FXML
    private TableColumn<Component, String> nameColumn;
    @FXML
    private TableColumn<Component, Integer> quantityColumn;

    private ObservableList<Component> componentData;

    public ComponentController() {
        componentService = new ComponentService();
        componentData = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadComponents();
    }

    private void loadComponents() {
        List<Component> components = componentService.getAllComponents();
        componentTable.getItems().setAll(components);
    }

    @FXML
    private void saveComponent() {
        Component component = new Component();
        component.setName(nameField.getText());
        component.setQuantity(Integer.parseInt(quantityField.getText()));
        componentService.saveComponent(component);
        loadComponents();
    }

    @FXML
    private void deleteComponent() {
        try {
            Integer id = Integer.parseInt(searchIdField.getText());
            componentService.deleteComponent(id);
            loadComponents();
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }

    }

    @FXML
    private void searchComponentById() {
        try {
            Integer id = Integer.parseInt(searchIdField.getText());
            Component component = componentService.findComponentById(id);
            if (component != null) {
                componentData.setAll(component);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void updateComponent() {
        String idText = searchIdField.getText();
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        if (idText.isEmpty() || name.isEmpty() || quantity < 0) {
            System.out.println("Please fill out all fields.");
            return;
        }

        try {
            Integer id = Integer.parseInt(idText);
            Component updatedComponent = componentService.updateComponent(id, name, quantity);
            if (updatedComponent != null) {
                System.out.println("Component updated successfully.");
                loadComponents();
            } else {
                System.out.println("Component with this ID not found.");
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
