package com.hcmuss.admin.controllers;

import com.hcmuss.admin.models.User;
import com.hcmuss.admin.utils.JsonListResponse;
import com.hcmuss.admin.utils.LoadVariable;
import com.hcmuss.admin.utils.TokenStorage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, LocalDateTime> createdAtColumn;

    @FXML
    private TableColumn<User, String> userRoleColumn;

    @FXML
    private TableColumn<User, Void> actionColumn;

    @FXML
    private Label totalAccount;

    @FXML
    private Label totalAccountAdmin;

    @FXML
    private Label totalAccountClient;

    @FXML
    private Pane paneAddAccount;

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        addActionButtons();
        getUsers();
    }

    private void addActionButtons() {
        actionColumn.setCellFactory(_ -> new TableCell<>() {
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

                editButton.setOnAction(_ -> {
                    User user = getTableView().getItems().get(getIndex());
                    handleEditUser(user);
                });

                deleteButton.setOnAction(_ -> {
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

    @FXML
    void addAccount(MouseEvent event) {
        switchToDashboard();
    }

    private void switchToDashboard() {
        try {
            FXMLLoader fxmlAddNews = new FXMLLoader(
                    getClass().getResource("/com/hcmuss/admin/fxml/account_add.fxml"));

            Scene newsScene = new Scene(fxmlAddNews.load(), 1280, 768);
            Stage stage = (Stage) paneAddAccount.getScene().getWindow();
            stage.setScene(newsScene);
            stage.setTitle("New add");
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEditUser(User user) {

    }

    private void handleDeleteUser(User user) {
        String BaseUrl = LoadVariable.get("BASE_URL");
        String url = BaseUrl + "/api/v1/users/" + user.getId();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + TokenStorage.getToken())
                .DELETE()
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {

                        userTableView.getItems().remove(user);
                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        deleteIcon.setIcon(FontAwesomeIcons.TRASH);

                        totalAccount.setText(String.valueOf(userTableView.getItems().size()));
                        long accountAdminCount = userTableView.getItems().stream()
                                .filter(a -> a.getUserRole().equals("admin"))
                                .count();
                        long accountClientCount = userTableView.getItems().stream()
                                .filter(a -> a.getUserRole().equals("client"))
                                .count();
                        totalAccountClient.setText(String.valueOf(accountClientCount));
                        totalAccountAdmin.setText(String.valueOf(accountAdminCount));
                        Notifications.create()
                                .title("Xóa thành công")
                                .text("Người dùng " + user.getFullName() + " đã được xóa!")
                                .graphic(deleteIcon)
                                .position(Pos.TOP_RIGHT)
                                .hideAfter(Duration.seconds(4))
                                .darkStyle()
                                .show();
                    } else {

                        Notifications.create()
                                .title("Lỗi")
                                .text("Không thể xóa người dùng. Mã lỗi: " + response.statusCode())
                                .showError();
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {

                    e.printStackTrace();
                });
            }
        }).start();
    }

    public void getUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String BaseUrl = LoadVariable.get("BASE_URL");
        String url = BaseUrl + "/api/v1/users";

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

                            JsonListResponse<User> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<User>>() {
                                    });

                            List<User> users = parsedResponse.getMessage();

                            userList.addAll(users);

                            if (userTableView == null) {

                                return;
                            }

                            userTableView.setItems(userList);

                            totalAccount.setText(String.valueOf(users.size()));
                            long accountAdminCount = users.stream().filter(a -> a.getUserRole().equals("admin"))
                                    .count();
                            long accountClientCount = users.stream().filter(a -> a.getUserRole().equals("client"))
                                    .count();
                            totalAccountClient.setText(String.valueOf(accountClientCount));
                            totalAccountAdmin.setText(String.valueOf(accountAdminCount));
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
