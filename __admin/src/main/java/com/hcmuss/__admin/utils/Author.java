package com.hcmuss.__admin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.concurrent.CompletableFuture;

public class Author {

    public static CompletableFuture<Boolean> HandleCheckLogin() {
        String tokenPayload = TokenStorage.getToken();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonRequest = objectMapper.createObjectNode();
        jsonRequest.put("token", tokenPayload);
        String requestBody = null;
        try {
            // Chuyển đối tượng JSON thành chuỗi
            requestBody = objectMapper.writeValueAsString(jsonRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }

        String url = "http://localhost:8080/api/v1/auth/check-token";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        CompletableFuture<Boolean> resultFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());


                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        try {

                            resultFuture.complete(true);


                        } catch (Exception e) {
                            e.printStackTrace();
                            resultFuture.complete(false);
                        }
                    } else {
                        resultFuture.complete(false);
                    }
                });

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                resultFuture.complete(false);
            }
        }).start();

        // Trả về CompletableFuture để có thể tiếp tục xử lý sau này
        return resultFuture;
    }
}