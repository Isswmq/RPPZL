package com.prodmaster.controller;

import com.prodmaster.entity.Component;
import com.prodmaster.entity.Product;
import com.prodmaster.service.ComponentService;
import com.prodmaster.service.ProductService;
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
    private final ComponentService componentService = new ComponentService();

    @FXML
    private TextField nameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<Component> componentTable;
    @FXML
    private TableColumn<Component, Integer> idColumn;
    @FXML
    private TableColumn<Component, String> nameColumn;
    @FXML
    private TableColumn<Component, Integer> quantityColumn;

    @FXML
    public void initialize() {
        // Настройка столбцов таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Загрузка данных
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
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}
