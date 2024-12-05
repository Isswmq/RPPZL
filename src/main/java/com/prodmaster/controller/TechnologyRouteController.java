package com.prodmaster.controller;

import com.prodmaster.entity.TechnologyRoute;
import com.prodmaster.entity.WorkCenter;
import com.prodmaster.service.TechnologyRouteService;
import com.prodmaster.service.WorkCenterService;
import com.prodmaster.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TechnologyRouteController {

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ComboBox<WorkCenter> workCenterComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private TableView<TechnologyRoute> technologyRouteTable;
    @FXML
    private TableColumn<TechnologyRoute, Integer> idColumn;
    @FXML
    private TableColumn<TechnologyRoute, String> nameColumn;
    @FXML
    private TableColumn<TechnologyRoute, String> descriptionColumn;

    private TechnologyRouteService technologyRouteService;
    private WorkCenterService workCenterService;
    private ObservableList<TechnologyRoute> technologyRouteData;

    public TechnologyRouteController() {
        technologyRouteService = new TechnologyRouteService();
        workCenterService = new WorkCenterService();
        technologyRouteData = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Настройка столбцов
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Загрузка данных в таблицу
        loadTechnologyRoutes();

        // Загружаем WorkCenters в ComboBox
        workCenterComboBox.setItems(FXCollections.observableArrayList(workCenterService.getAllWorkCenters()));
    }

    @FXML
    private void loadTechnologyRoutes() {
        technologyRouteData.setAll(technologyRouteService.getAllTechnologyRoutes());
        technologyRouteTable.setItems(technologyRouteData);
    }

    @FXML
    private void saveTechnologyRoute() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        WorkCenter workCenter = workCenterComboBox.getValue();

        // Валидация
        if (name.isEmpty() || description.isEmpty() || workCenter == null) {
            System.out.println("Please fill out all fields.");
            return;
        }

        // Создание нового объекта TechnologyRoute
        TechnologyRoute technologyRoute = new TechnologyRoute();
        technologyRoute.setName(name);
        technologyRoute.setDescription(description);
        technologyRoute.setWorkCenter(workCenter);

        // Сохранение через сервис
        technologyRouteService.saveTechnologyRoute(technologyRoute);

        // Обновление таблицы после сохранения
        loadTechnologyRoutes();

        // Очистка полей
        nameField.clear();
        descriptionField.clear();
        workCenterComboBox.setValue(null);
    }

    @FXML
    private void goToMainScreen() throws IOException {
        Stage stage = (Stage) nameField.getScene().getWindow();
        SceneSwitcher.switchTo("/view/main.fxml", stage);
    }
}
