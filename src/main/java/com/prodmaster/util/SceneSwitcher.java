package com.prodmaster.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {
    public static void switchTo(String fxmlPath, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
        Parent root = loader.load();

        // Установим новую сцену на переданный stage
        stage.setScene(new Scene(root));
        stage.show();
    }
}
