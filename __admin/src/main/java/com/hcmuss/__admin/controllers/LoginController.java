package com.hcmuss.__admin.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcmuss.__admin.dtos.MessageLogin;
import com.hcmuss.__admin.models.LoginRequest;
import com.hcmuss.__admin.utils.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private PasswordField password;

    @FXML
    private TextField username;


    @FXML
    protected void handleLogin() {
        String usernameInput = username.getText();
        String passwordInput = password.getText();

        // Kiểm tra nếu tên đăng nhập và mật khẩu không được để trống
        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            showAlert("Lỗi", "Tên đăng nhập và mật khẩu không được để trống!");
            return;
        }

        // Tạo đối tượng yêu cầu đăng nhập
        LoginRequest loginRequest = new LoginRequest(usernameInput, passwordInput);

        ObjectMapper objectMapper = new ObjectMapper();
        String loginData = null;
        try {
            // Chuyển đối tượng thành chuỗi JSON
            loginData = objectMapper.writeValueAsString(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Lỗi", "Đã có lỗi xảy ra khi chuẩn bị dữ liệu đăng nhập.");
            return;
        }

        String url = "http://localhost:8080/api/v1/auth/login";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(loginData))
                .build();

        // Gửi yêu cầu đăng nhập tới server trong một luồng riêng
        new Thread(() -> {
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                // Đảm bảo giao diện được cập nhật trên JavaFX thread
                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        try {
                            // Parse response body thành JsonObjectResponse<MessageLogin>
                            JsonObjectResponse<MessageLogin> parsedResponse = objectMapper.readValue(
                                    response.body(),
                                    new TypeReference<JsonObjectResponse<MessageLogin>>() {
                                    }
                            );

                            // Lấy token từ response
                            MessageLogin message = parsedResponse.getMessage();
                            String token = message.getToken();


                            // Lưu token hoặc xử lý thêm nếu cần
                            TokenStorage.saveToken(token);

                            // Chuyển cảnh sang Dashboard sau khi đăng nhập thành công
                            switchToDashboard();

                        } catch (Exception e) {
                            e.printStackTrace();
                            showAlert("Lỗi", "Đã xảy ra lỗi khi xử lý phản hồi từ server.");
                        }
                    } else {
                        showAlert("Lỗi", "Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");
                    }
                });

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                showAlert("Lỗi kết nối", "Không thể kết nối tới máy chủ. Vui lòng thử lại.");
            }
        }).start();
    }


    private void switchToDashboard() {
        try {
            FXMLLoader fxmlLoaderDashboard = new FXMLLoader(getClass().getResource("/com/hcmuss/__admin/fxml/dashboard.fxml"));

            Scene dashboardScene = new Scene(fxmlLoaderDashboard.load(), 1280, 768);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.setTitle("Dashboard");
            helpers.SwitchScene(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
