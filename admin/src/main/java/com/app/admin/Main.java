package com.app.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Scene scene1;
    private Scene scene2;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("scene1-view.fxml"));
        scene1 = new Scene(fxmlLoader1.load(), 320, 240);

        FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("scene2-view.fxml"));
        scene2 = new Scene(fxmlLoader2.load(), 320, 240);

        stage.setTitle("Dashboard Garage Car!");
        stage.setScene(scene1);
        stage.show();

        Button switchToScene2Button = new Button("Switch to Scene 2");
        switchToScene2Button.setOnAction(e -> stage.setScene(scene2));
        ((StackPane) fxmlLoader1.getRoot()).getChildren().add(switchToScene2Button);

        Button switchToScene1Button = new Button("Switch to Scene 1");
        switchToScene1Button.setOnAction(e -> stage.setScene(scene1));
        ((StackPane) fxmlLoader2.getRoot()).getChildren().add(switchToScene1Button);
    }

    public static void main(String[] args) {
        launch();
    }
}
