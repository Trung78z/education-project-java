package com.hcmuss.__admin.utils;

import javafx.application.Platform;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Author {

    public static CompletableFuture<Boolean> HandleCheckLogin() {



        String url = "http://localhost:8080/api/v1/auth/check-token";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TokenStorage.getToken())
                .GET()
                .build();

        CompletableFuture<Boolean> resultFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                System.out.println("Sending request to URL: " + url);
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        resultFuture.complete(true);
                    } else {
                        System.err.println("Server responded with an error: " + response.body());
                        resultFuture.complete(false);
                    }
                });

            } catch (ConnectException e) {
                System.err.println("Failed to connect to server: " + e.getMessage());
                Platform.runLater(() -> resultFuture.complete(false)); // Đảm bảo giao diện được cập nhật

            } catch (IOException e) {
                System.err.println("Network error: " + e.getMessage());
                Platform.runLater(() -> resultFuture.complete(false));

            } catch (InterruptedException e) {
                System.err.println("Request was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Khôi phục trạng thái interrupted
                Platform.runLater(() -> resultFuture.complete(false));
            }
        }).start();

        return resultFuture;
    }
}