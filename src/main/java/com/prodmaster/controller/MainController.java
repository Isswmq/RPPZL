package com.prodmaster.controller;

import com.prodmaster.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    public Label label;


    @FXML
    private Button goToProductButton;

    private Stage getStage() {
        return (Stage) label.getScene().getWindow();  // Получите Stage из текущей кнопки или другого элемента
    }

    @FXML
    private void goToProduct() throws IOException {
        SceneSwitcher.switchTo("/view/ProductView.fxml", getStage());
    }

    @FXML
    private void goToComponent() throws IOException {
        SceneSwitcher.switchTo("/view/Component.fxml", getStage());
    }

    @FXML
    private void goToSpecification() throws IOException {
        SceneSwitcher.switchTo("/view/SpecificationView.fxml", getStage());
    }

    @FXML
    private void goToWorkCenter() throws IOException {
        SceneSwitcher.switchTo("/view/WorkCenterView.fxml", getStage());
    }

    @FXML
    private void goToTechnologyRoute() throws IOException {
        SceneSwitcher.switchTo("/view/TechnologyRouteView.fxml", getStage());
    }

    @FXML
    private void goToOperation() throws IOException {
        SceneSwitcher.switchTo("/view/Operation.fxml", getStage());
    }
}
