package com.hcmuss.__admin.controllers;




import com.hcmuss.__admin.utils.Helpers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class LoginController {

    private Helpers helpers = new Helpers();



    @FXML
    private Label text_label;

    @FXML
    private Button loginButton;

    @FXML
    public void handleFetchData() {
        System.out.println("Data");
        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        System.out.println(response.body());
                        text_label.setText(String.valueOf(response.statusCode()));
//                        responseArea.setText(response.body());
                    } else {
//                        responseArea.setText("Error: " + response.statusCode());
                    }
                });
            } catch (Exception e) {
//                javafx.application.Platform.runLater(() -> responseArea.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }

    @FXML
    protected void handleLogin()  {
        try {

            FXMLLoader fxmlLoaderDashboard = new FXMLLoader(getClass().getResource("/com/hcmuss/__admin/fxml/dashboard.fxml"));

            Scene dashboardScene = new Scene(fxmlLoaderDashboard.load(), 1280, 768);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.setTitle("Dashboard");
            helpers.SwitchScene(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
