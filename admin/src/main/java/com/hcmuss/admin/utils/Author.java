package com.hcmuss.admin.utils;

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
        String BaseUrl = LoadVariable.get("BASE_URL");

        String url = BaseUrl + "/api/v1/auth/check-token";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TokenStorage.getToken())
                .GET()
                .build();

        CompletableFuture<Boolean> resultFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {

                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        resultFuture.complete(true);
                    } else {

                        resultFuture.complete(false);
                    }
                });

            } catch (ConnectException e) {

                Platform.runLater(() -> resultFuture.complete(false)); // Đảm bảo giao diện được cập nhật

            } catch (IOException e) {

                Platform.runLater(() -> resultFuture.complete(false));

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt(); // Khôi phục trạng thái interrupted
                Platform.runLater(() -> resultFuture.complete(false));
            }
        }).start();

        return resultFuture;
    }
}