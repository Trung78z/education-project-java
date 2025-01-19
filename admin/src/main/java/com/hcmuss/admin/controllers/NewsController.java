package com.hcmuss.admin.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hcmuss.admin.models.News;

import com.hcmuss.admin.utils.JsonListResponse;
import com.hcmuss.admin.utils.LoadVariable;
import com.hcmuss.admin.utils.TokenStorage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URI;
import java.net.http.*;
import java.util.List;

public class NewsController {

    @FXML
    private TableView<News> newsTable;
    @FXML
    private TableColumn<News, Integer> colId;
    @FXML
    private TableColumn<News, String> colTitle;
    @FXML
    private TableColumn<News, String> colDescription;
    @FXML
    private TableColumn<News, String> colCategory;
    @FXML
    private TableColumn<News, Boolean> colPublicNew;
    @FXML
    private TableColumn<News, String> colCreatedAt;
    @FXML
    private TableColumn<News, Void> actionColumn;

    @FXML
    private Label totalNews;

    @FXML
    private Label totalNewsPrivate;

    @FXML
    private Label totalNewsPublic;

    @FXML
    private Pane paneAddNews;

    HttpClient client = HttpClient.newHttpClient();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNewCategory().getName()));
        colPublicNew.setCellValueFactory(new PropertyValueFactory<>("publicNew"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        addActionButtons();
        getNews();
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
                    News news = getTableView().getItems().get(getIndex());
                    handleEditNew(news);
                });

                deleteButton.setOnAction(_ -> {
                    News news = getTableView().getItems().get(getIndex());
                    handleDeleteNew(news);
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

    private void handleEditNew(News news) {

    }

    @FXML
    void addNews(MouseEvent event) {
        switchToDashboard();
    }

    private void switchToDashboard() {
        try {
            FXMLLoader fxmlAddNews = new FXMLLoader(getClass().getResource("/com/hcmuss/admin/fxml/news_add.fxml"));

            Scene newsScene = new Scene(fxmlAddNews.load(), 1280, 768);
            Stage stage = (Stage) paneAddNews.getScene().getWindow();
            stage.setScene(newsScene);
            stage.setTitle("New add");
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteNew(News news) {
        String BaseUrl = LoadVariable.get("BASE_URL");

        String url = BaseUrl + "/api/v1/new/" + news.getId();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).header("Authorization", "Bearer " + TokenStorage.getToken())
                .DELETE()
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    System.out.println(response);
                    if (response.statusCode() == 200) {

                        newsTable.getItems().remove(news);
                        totalNews.setText(String.valueOf(newsTable.getItems().size()));

                        long privateNewsCount = newsTable.getItems().stream().filter(n -> !n.getPublicNew()).count();
                        long publicNewsCount = newsTable.getItems().stream().filter(n -> n.getPublicNew()).count();

                        totalNewsPrivate.setText(String.valueOf(privateNewsCount));
                        totalNewsPublic.setText(String.valueOf(publicNewsCount));

                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        deleteIcon.setIcon(FontAwesomeIcons.TRASH);
                        Notifications.create()
                                .title("Xóa thành công")
                                .text("News " + news.getTitle() + " đã được xóa!")
                                .graphic(deleteIcon)
                                .position(Pos.TOP_RIGHT)
                                .hideAfter(Duration.seconds(4))
                                .darkStyle()
                                .show();
                    } else {

                        Notifications.create()
                                .title("Lỗi")
                                .text("Không thể xóa News. Mã lỗi: " + response.statusCode())
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

    public void getNews() {
        String BaseUrl = LoadVariable.get("BASE_URL");
        ObservableList<News> newsList = FXCollections.observableArrayList();
        String url = BaseUrl + "/api/v1/new";

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

                            JsonListResponse<News> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<News>>() {
                                    });

                            List<News> news = parsedResponse.getMessage();

                            newsList.addAll(news);

                            if (newsTable == null) {

                                return;
                            }

                            newsTable.setItems(newsList);
                            totalNews.setText(String.valueOf(news.size()));

                            long privateNewsCount = news.stream().filter(n -> !n.getPublicNew()).count();
                            long publicNewsCount = news.stream().filter(n -> n.getPublicNew()).count();

                            totalNewsPrivate.setText(String.valueOf(privateNewsCount));
                            totalNewsPublic.setText(String.valueOf(publicNewsCount));

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
