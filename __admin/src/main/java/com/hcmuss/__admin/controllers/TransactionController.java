package com.hcmuss.__admin.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hcmuss.__admin.models.Transaction;
import com.hcmuss.__admin.utils.JsonListResponse;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionController {

    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, Integer> idColumn;
    @FXML
    private TableColumn<Transaction, String> userIdColumn;
    @FXML
    private TableColumn<Transaction, String> userEmailColumn;
    @FXML
    private TableColumn<Transaction, Integer> productIdColumn;
    @FXML
    private TableColumn<Transaction, String> productNameColumn;
    @FXML
    private TableColumn<Transaction, Integer> quantityColumn;
    @FXML
    private TableColumn<Transaction, Double> totalPriceColumn;
    @FXML
    private TableColumn<Transaction, LocalDateTime> createdAtColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        getTransaction();
    }

    public void getTransaction() {
        ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
        String url = "http://localhost:8080/api/v1/transaction"; // Correct URL

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

                            JsonListResponse<Transaction> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<Transaction>>() {
                                    });

                            List<Transaction> transactions = parsedResponse.getMessage();

                            transactionList.addAll(transactions);

                            if (transactionTable == null) {
                                System.err.println(
                                        "Error: transactionTable is null. Check FXML and controller bindings.");
                                return;
                            }

                            transactionTable.setItems(transactionList);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println(
                                "Error: Failed to fetch transactions. Status code: " + httpResponse.statusCode());
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
