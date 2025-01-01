package com.hcmuss.__admin.controllers;

import com.hcmuss.__admin.models.User;
import com.hcmuss.__admin.utils.JsonListResponse;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.scene.layout.HBox;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class AccountController {


    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> fullNameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, LocalDateTime> createdAtColumn;

    @FXML
    private TableColumn<User, String> userRoleColumn;

    @FXML
    private Label text_label;
    @FXML
    private TableColumn<User, Void> actionColumn;

    HttpClient client = HttpClient.newHttpClient();

    @FXML
    public void initialize() {

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        addActionButtons();
        getUsers();
    }


    private void addActionButtons() {
        actionColumn.setCellFactory(column -> new TableCell<>() {
            private final Button editButton = new Button();
            private final Button deleteButton = new Button();
            private final HBox actionBox = new HBox(6, editButton, deleteButton);

            {
                FontAwesomeIcon editIcon = new FontAwesomeIcon();
                editIcon.setIcon(FontAwesomeIcons.EDIT);
                editIcon.setSize("14px");
                editButton.setGraphic(editIcon);

                FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                deleteIcon.setIcon(FontAwesomeIcons.TRASH);
                deleteIcon.setSize("14px");
                deleteButton.setGraphic(deleteIcon);

                editButton.setPrefSize(25, 25);
                deleteButton.setPrefSize(25, 25);


                actionBox.setSpacing(5);


                editButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    handleEditUser(user);
                });


                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    handleDeleteUser(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionBox);
                }
            }
        });
    }


    private void handleEditUser(User user) {
        System.out.println("Editing user: " + user);

    }

    private void handleDeleteUser(User user) {
        System.out.println("Deleting user: " + user);


        String url = "http://localhost:8080/api/v1/users/" + user.getId();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        System.out.println("User deleted successfully.");
                        
                        userTableView.getItems().remove(user);
                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        deleteIcon.setIcon(FontAwesomeIcons.TRASH);
                        Notifications.create()
                                .title("Xóa thành công")
                                .text("Người dùng " + user.getFullName() + " đã được xóa!")
                                .graphic(deleteIcon)
                                .position(Pos.TOP_RIGHT)
                                .hideAfter(Duration.seconds(4))
                                .darkStyle()
                                .show();
                    } else {
                        System.err.println("Failed to delete user. HTTP Status: " + response.statusCode());
                        Notifications.create()
                                .title("Lỗi")
                                .text("Không thể xóa người dùng. Mã lỗi: " + response.statusCode())
                                .showError();
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    System.err.println("Error deleting user: " + e.getMessage());
                    e.printStackTrace();
                });
            }
        }).start();
    }


    public void getUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String url = "http://localhost:8080/api/v1/users";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (httpResponse.statusCode() == 200) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.registerModule(new JavaTimeModule());

                            JsonListResponse<User> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<User>>() {
                                    }
                            );

                            List<User> users = parsedResponse.getMessage();
                            for (User user : users) {
                                System.out.println(user);
                            }
                            userList.addAll(users);


                            if (userTableView == null) {
                                System.err.println("Error: userTableView is null. Check FXML and controller bindings.");
                                return;
                            }

                            userTableView.setItems(userList);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Platform.runLater(() -> text_label.setText("JSON Parsing Error: " + e.getMessage()));
                        }
                    } else {
                        Platform.runLater(() -> text_label.setText("HTTP Error: " + httpResponse.statusCode()));
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    e.printStackTrace();
                    text_label.setText("Request Exception: " + e.getMessage());
                });
            }
        }).start();
    }


    @FXML
    public void handleFetchData() {
        
        System.out.println("Data");
        String url = "http://localhost:8080/api/v1/users";


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

                    } else {

                    }
                });
            } catch (Exception e) {

            }
        }).start();
    }


}
