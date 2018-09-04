package com.wiktor.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/com/wiktor/fxml/MainWindow.fxml"));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(getClass().getResource("/com/wiktor/fxml/Style.css").toExternalForm());
            primaryStage.getIcons().add(new Image("/com/wiktor/images/XY.png"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Star and Heart Challange | v1.0");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
