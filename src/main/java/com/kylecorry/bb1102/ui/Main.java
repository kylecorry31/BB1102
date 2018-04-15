package com.kylecorry.bb1102.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add("/style/style.css");
        stage.setTitle("BB 1102");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
