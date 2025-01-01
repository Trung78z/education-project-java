package com.hcmuss.__admin;

import com.hcmuss.__admin.utils.Author;
import com.hcmuss.__admin.utils.TokenStorage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) {

        Author.HandleCheckLogin().thenAccept(result -> {
            if (result) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/dashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1280, 768);

                    String globalCss = Main.class.getResource("css/global.css").toExternalForm();
                    scene.getStylesheets().add(globalCss);

                    stage.setResizable(false);
                    stage.setTitle("Login with box car!");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/login.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 600);

                    String globalCss = Main.class.getResource("css/global.css").toExternalForm();
                    scene.getStylesheets().add(globalCss);
                    stage.setResizable(false);

                    stage.setTitle("Login with box car!");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}