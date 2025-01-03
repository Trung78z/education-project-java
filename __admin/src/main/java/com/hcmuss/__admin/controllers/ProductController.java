package com.hcmuss.__admin.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hcmuss.__admin.models.Product;

import com.hcmuss.__admin.utils.JsonListResponse;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URI;
import java.net.http.*;
import java.util.List;

public class ProductController {


    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> colId;
    @FXML
    private TableColumn<Product, String> colName;
    @FXML
    private TableColumn<Product, Double> colPrice;
    @FXML
    private TableColumn<Product, Integer> colQuantity;
    @FXML
    private TableColumn<Product, String> colType;
    @FXML
    private TableColumn<Product, Double> colDiscount;
    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, Void> actionColumn;
    HttpClient client = HttpClient.newHttpClient();


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
                    Product product = getTableView().getItems().get(getIndex());
                    handleEditUser(product);
                });


                deleteButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
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


    private void handleEditUser(Product product) {
        System.out.println("Editing product: " + product);

    }

    private void handleDeleteUser(Product product) {


        String url = "http://localhost:8080/api/v1/product/" + product.getId();
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

                        productTable.getItems().remove(product);
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

    public void getProducts() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
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

                            JsonListResponse<Product> parsedResponse = objectMapper.readValue(
                                    httpResponse.body(),
                                    new TypeReference<JsonListResponse<Product>>() {
                                    }
                            );
                            List<Product> products = parsedResponse.getMessage();
                            productList.addAll(products);


                            if (productTable == null) {
                                System.err.println("Error: productTable is null. Check FXML and controller bindings.");
                                return;
                            }

                            productTable.setItems(productList);
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
