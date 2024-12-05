package com.prodmaster.controller;

import com.prodmaster.entity.Product;
import com.prodmaster.entity.Specification;
import com.prodmaster.service.ProductService;
import com.prodmaster.service.SpecificationService;
import com.prodmaster.util.SceneSwitcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class SpecificationController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField versionField;

    @FXML
    private TextField statusField;

    @FXML
    private ComboBox<Product> productComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private TableView<Specification> specificationTable;

    @FXML
    private TableColumn<Specification, Integer> idColumn;

    @FXML
    private TableColumn<Specification, String> nameColumn;

    @FXML
    private TableColumn<Specification, String> versionColumn;

    @FXML
    private TableColumn<Specification, String> statusColumn;

    @FXML
    private TableColumn<Specification, String> productColumn;

    private SpecificationService specificationService;
    private ProductService productService;
    private ObservableList<Specification> specificationData;
    private ObservableList<Product> productData;

    public SpecificationController() {
        specificationService = new SpecificationService();
        productService = new ProductService();
        specificationData = FXCollections.observableArrayList();
        productData = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Настройка столбцов таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        productColumn.setCellValueFactory(data ->
                data.getValue().getProduct() != null
                        ? new SimpleStringProperty(data.getValue().getProduct().getName())
                        : new SimpleStringProperty("None")
        );

        // Загрузка данных
        loadSpecifications();
        loadProducts();
    }

    @FXML
    private void loadSpecifications() {
        specificationData.setAll(specificationService.getAllSpecializations());
        specificationTable.setItems(specificationData);
    }

    @FXML
    private void loadProducts() {
        productData.setAll(productService.getAllProducts());
        productComboBox.setItems(productData);
    }

    @FXML
    private void saveSpecification() {
        String name = nameField.getText();
        String version = versionField.getText();
        String status = statusField.getText();
        Product selectedProduct = productComboBox.getValue();

        if (name.isEmpty() || version.isEmpty() || status.isEmpty() || selectedProduct == null) {
            System.out.println("Please fill out all fields and select a product.");
            return;
        }

        Specification specification = new Specification();
        specification.setName(name);
        specification.setVersion(version);
        specification.setStatus(status);
        specification.setProduct(selectedProduct);

        specificationService.saveSpecialization(specification);

        // Обновление таблицы после сохранения
        loadSpecifications();

        // Очистка полей
        nameField.clear();
        versionField.clear();
        statusField.clear();
        productComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}

