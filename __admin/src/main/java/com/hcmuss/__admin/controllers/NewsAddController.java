package com.hcmuss.__admin.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.hcmuss.__admin.models.NewCategory;
import com.hcmuss.__admin.models.News;
import com.hcmuss.__admin.utils.ApiResponse;
import com.hcmuss.__admin.utils.JsonListResponse;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

public class NewsAddController {
    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private ComboBox<String> publicComboBox;

    @FXML
    private ComboBox<NewCategory> categoryComboBox;

    @FXML
    private Text url_image;

    @FXML
    private Button selectFileButton;

    private String image;

    public void initialize() {
        publicComboBox.setItems(FXCollections.observableArrayList("True", "False"));
        publicComboBox.getSelectionModel().select(0);

        Platform.runLater(() -> {
            if (htmlEditor != null) {

                htmlEditor.setHtmlText("<h1>Welcome</h1><p>Start typing...</p>");
            }
        });
        fetchCategoriesFromApi();

    }

    @FXML
    public void saveNews(ActionEvent event) {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String content = htmlEditor.getHtmlText();
        String imageUrl = image;
        boolean isPublic = publicComboBox.getValue() != null && publicComboBox.getValue().equals("False");
        NewCategory selectedCategory = categoryComboBox.getValue();
        System.out.println(imageUrl);
        if (title == null || title.isEmpty() ||
                description == null || description.isEmpty() ||
                content == null || content.isEmpty() ||
                imageUrl == null || imageUrl.isEmpty() ||
                selectedCategory == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
            return;
        }
        News news = new News();
        news.setTitle(title);
        news.setDescription(description);
        news.setContent(content);
        news.setImage(imageUrl);
        news.setPublicNew(isPublic);
        news.setNewCategory(selectedCategory);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(news);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/v1/new"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
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
                try {
                    FXMLLoader fxmlLoaderDashboard = new FXMLLoader(
                            getClass().getResource("/com/hcmuss/__admin/fxml/dashboard.fxml"));
                    Scene dashboardScene = new Scene(fxmlLoaderDashboard.load(), 1280, 768);
                    Stage stage = (Stage) titleField.getScene().getWindow();
                    stage.setScene(dashboardScene);
                    stage.setTitle("Dashboard");
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add news: " + message);
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(selectFileButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                System.out.println("Base64 Encoded String: " + encodedString);
                url_image.setText(selectedFile.getName());
                image = encodedString;
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("File Error", "Could not read the file.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void fetchCategoriesFromApi() {
        String url = "http://localhost:8080/api/v1/news-category";
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

                            JsonListResponse<NewCategory> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<NewCategory>>() {
                                    });

                            List<NewCategory> categories = parsedResponse.getMessage();

                            ObservableList<NewCategory> categoryList = FXCollections.observableArrayList(categories);

                            categoryComboBox.setItems(categoryList);

                            categoryComboBox.setCellFactory(comboBox -> new ListCell<>() {
                                @Override
                                protected void updateItem(NewCategory item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getName());
                                    }
                                }
                            });
                            categoryComboBox.setButtonCell(new ListCell<>() {
                                @Override
                                protected void updateItem(NewCategory item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getName());
                                    }
                                }
                            });
                            if (!categoryList.isEmpty()) {
                                categoryComboBox.getSelectionModel().select(0);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("API request failed with status code: " + httpResponse.statusCode());
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
