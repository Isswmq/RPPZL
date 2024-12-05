package com.prodmaster.controller;

import com.prodmaster.dao.ProductDAO;
import com.prodmaster.entity.Product;
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


public class ProductController {
    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button saveButton;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Long> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    private ProductService productService;
    private ObservableList<Product> productData;

    public ProductController() {
        productService = new ProductService();
        productData = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Настройка столбцов для отображения данных
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Загрузка данных в таблицу
        loadProducts();
    }

    @FXML
    private void loadProducts() {
        productData.setAll(productService.getAllProducts());
        productTable.setItems(productData);
    }

    @FXML
    private void saveProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        if (name.isEmpty() || description.isEmpty()) {
            // Дополнительная валидация может быть добавлена
            System.out.println("Please fill out all fields.");
            return;
        }

        Product product = new Product(name, description);
        productService.saveProduct(product);

        // Обновление таблицы после сохранения
        loadProducts();

        // Очистка полей
        nameField.clear();
        descriptionField.clear();
    }
    @FXML
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}
