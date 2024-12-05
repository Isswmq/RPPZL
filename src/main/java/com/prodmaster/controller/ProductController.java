package com.prodmaster.controller;

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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;


public class ProductController {

    private boolean isAscending = true;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button sortButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchIdField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Long> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, BigDecimal> priceColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private TableColumn<Product, LocalDateTime> createdDateColumn;

    private ProductService productService;

    private ObservableList<Product> productData;

    public ProductController() {
        productService = new ProductService();
        productData = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));

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
        BigDecimal price = new BigDecimal(priceField.getText());
        if (name.isEmpty() || description.isEmpty()) {
            System.out.println("Please fill out all fields.");
            return;
        }

        Product product = new Product(name, description, price);
        productService.saveProduct(product);

        loadProducts();

        nameField.clear();
        priceField.clear();
        descriptionField.clear();
    }

    @FXML
    private void deleteProduct() {
        try {
            Integer id = Integer.parseInt(searchIdField.getText());
            productService.deleteProduct(id);
            loadProducts();
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void searchProductById() {
        try {
            Integer id = Integer.parseInt(searchIdField.getText());
            Product product = productService.getProductById(id);
            if (product != null) {
                productData.setAll(product);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void updateProduct() {
        String idText = searchIdField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        BigDecimal price = new BigDecimal(priceField.getText());

        if (idText.isEmpty() || name.isEmpty() || description.isEmpty()) {
            System.out.println("Please fill out all fields.");
            return;
        }

        try {
            Integer id = Integer.parseInt(idText);
            Product updatedProduct = productService.updateProduct(id, name, description, price);
            if (updatedProduct != null) {
                System.out.println("Product updated successfully.");
                loadProducts();
            } else {
                System.out.println("Product with this ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    @FXML
    private void sortProductsByPrice() {
        if (isAscending) {
            FXCollections.sort(productData, Comparator.comparing(Product::getPrice));
        } else {
            FXCollections.sort(productData, Comparator.comparing(Product::getPrice).reversed());
        }
        productTable.setItems(productData);
        isAscending = !isAscending;
    }

    @FXML
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}
