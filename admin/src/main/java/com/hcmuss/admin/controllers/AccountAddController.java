package com.hcmuss.admin.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hcmuss.admin.models.Role;
import com.hcmuss.admin.models.UserPayload;
import com.hcmuss.admin.utils.ApiResponse;
import com.hcmuss.admin.utils.JsonListResponse;
import com.hcmuss.admin.utils.LoadVariable;
import com.hcmuss.admin.utils.TokenStorage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AccountAddController {
    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private TextField usernameField;

    public void initialize() {
        fetchCategoriesFromApi();
    }

    @FXML
    void saveUser(ActionEvent event) {
        String address = addressField.getText();
        String email = emailField.getText();
        String fullName = fullNameField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        Role selectedRole = roleComboBox.getValue();
        String username = usernameField.getText();

        if (address == null || address.isEmpty() ||
                email == null || email.isEmpty() ||
                fullName == null || fullName.isEmpty() ||
                password == null || password.isEmpty() ||
                phone == null || phone.isEmpty() ||
                selectedRole == null ||
                username == null || username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
            return;
        }

        UserPayload user = new UserPayload();
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setUserRole(selectedRole);
        user.setPassword(password);
        user.setAddress(address);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(user);

            HttpClient client = HttpClient.newHttpClient();
            String BaseUrl = LoadVariable.get("BASE_URL");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BaseUrl + "/api/v1/users"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + TokenStorage.getToken())
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);
            boolean success = apiResponse.isSuccess();
            Object message = apiResponse.getMessage();

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("News added successfully!");
                alert.showAndWait();

                // Switch to dashboard
                switchToDashboard();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add account: " + message);
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void switchToDashboard() {
        try {
            FXMLLoader fxmlLoaderDashboard = new FXMLLoader(
                    getClass().getResource("/com/hcmuss/admin/fxml/dashboard.fxml"));
            Scene dashboardScene = new Scene(fxmlLoaderDashboard.load(), 1280, 768);
            Stage stage = (Stage) phoneField.getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void closeUser(ActionEvent event) {
        switchToDashboard();

    }

    private void fetchCategoriesFromApi() {
        String BaseUrl = LoadVariable.get("BASE_URL");
        String url = BaseUrl + "/api/v1/role-user";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        new Thread(() -> {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (httpResponse.statusCode() == 200) {
                        try {

                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.registerModule(new JavaTimeModule());

                            JsonListResponse<Role> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<Role>>() {
                                    });

                            List<Role> categories = parsedResponse.getMessage();

                            ObservableList<Role> categoryList = FXCollections.observableArrayList(categories);

                            roleComboBox.setItems(categoryList);

                            roleComboBox.setCellFactory(_ -> new ListCell<>() {
                                @Override
                                protected void updateItem(Role item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getRoleName());
                                    }
                                }
                            });
                            roleComboBox.setButtonCell(new ListCell<>() {
                                @Override
                                protected void updateItem(Role item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getRoleName());
                                    }
                                }
                            });
                            if (!categoryList.isEmpty()) {
                                roleComboBox.getSelectionModel().select(0);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    e.printStackTrace();
                });
            }
        }).start();
    }

}
