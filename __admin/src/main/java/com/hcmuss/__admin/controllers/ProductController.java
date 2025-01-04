package com.hcmuss.__admin.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hcmuss.__admin.models.product.ProductResponse;

import com.hcmuss.__admin.utils.JsonListResponse;
import com.hcmuss.__admin.utils.TokenStorage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.application.Platform;
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

public class ProductController {

    @FXML
    private TableView<ProductResponse> productTable;
    @FXML
    private TableColumn<ProductResponse, Integer> colId;
    @FXML
    private TableColumn<ProductResponse, String> colName;
    @FXML
    private TableColumn<ProductResponse, Double> colPrice;
    @FXML
    private TableColumn<ProductResponse, Integer> colQuantity;
    @FXML
    private TableColumn<ProductResponse, String> colType;
    @FXML
    private TableColumn<ProductResponse, Double> colDiscount;
    @FXML
    private TableColumn<ProductResponse, String> categoryColumn;

    @FXML
    private TableColumn<ProductResponse, Void> actionColumn;
    HttpClient client = HttpClient.newHttpClient();

    @FXML
    private Label priceMax;

    @FXML
    private Label priceMin;

    @FXML
    private Label totalProduct;

    @FXML
    private Pane paneAddProduct;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("productBrand"));

        addActionButtons();
        getProducts();
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
                    ProductResponse product = getTableView().getItems().get(getIndex());
                    handleEditUser(product);
                });

                deleteButton.setOnAction(event -> {
                    ProductResponse product = getTableView().getItems().get(getIndex());
                    handleDeleteUser(product);
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

    private void handleEditUser(ProductResponse product) {
        System.out.println("Editing product: " + product);

    }

    private void handleDeleteUser(ProductResponse product) {

        String url = "http://localhost:8080/api/v1/product/" + product.getId();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).header("Authorization", "Bearer " + TokenStorage.getToken())
                .DELETE()
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (response.statusCode() == 200) {
                        System.out.println("User deleted successfully.");

                        productTable.getItems().remove(product);
                        int totalProducts = productTable.getItems().size();
                        totalProduct.setText(String.valueOf(totalProducts));
                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        deleteIcon.setIcon(FontAwesomeIcons.TRASH);
                        Notifications.create()
                                .title("Xóa thành công")
                                .text("Xe " + product.getName() + " đã được xóa!")
                                .graphic(deleteIcon)
                                .position(Pos.TOP_RIGHT)
                                .hideAfter(Duration.seconds(4))
                                .darkStyle()
                                .show();
                    } else {

                        Notifications.create()
                                .title("Lỗi")
                                .text("Không thể xóa xe. Mã lỗi: " + response.statusCode())
                                .showError();
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    System.err.println("Error deleting product: " + e.getMessage());
                    e.printStackTrace();
                });
            }
        }).start();
    }

    @FXML
    void addProduct(MouseEvent event) {
        switchToDashboard();
    }

    private void switchToDashboard() {
        try {

            FXMLLoader fxmlAddNews = new FXMLLoader(
                    getClass().getResource("/com/hcmuss/__admin/fxml/product_add.fxml"));
            Scene newsScene = new Scene(fxmlAddNews.load(), 1280, 768);
            Stage stage = (Stage) paneAddProduct.getScene().getWindow();
            stage.setScene(newsScene);
            stage.setTitle("New Product");
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getProducts() {
        ObservableList<ProductResponse> productList = FXCollections.observableArrayList();
        String url = "http://localhost:8080/api/v1/product";
        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        new Thread(() -> {
            try {
                HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

                Platform.runLater(() -> {
                    if (httpResponse.statusCode() == 200) {
                        try {

                            JsonListResponse<ProductResponse> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<ProductResponse>>() {
                                    });
                            List<ProductResponse> products = parsedResponse.getMessage();
                            productList.addAll(products);

                            if (productTable == null) {
                                System.err.println("Error: productTable is null. Check FXML and controller bindings.");
                                return;
                            }

                            productTable.setItems(productList);

                            double maxPrice = productList.stream().mapToDouble(ProductResponse::getPrice).max()
                                    .orElse(0);
                            double minPrice = productList.stream().mapToDouble(ProductResponse::getPrice).min()
                                    .orElse(0);
                            int totalProducts = productList.size();

                            priceMax.setText(String.format("%.2f", maxPrice));
                            priceMin.setText(String.format("%.2f", minPrice));
                            totalProduct.setText(String.valueOf(totalProducts));

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
