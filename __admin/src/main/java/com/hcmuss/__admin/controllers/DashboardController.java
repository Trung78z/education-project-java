package com.hcmuss.__admin.controllers;

import com.hcmuss.__admin.utils.Helpers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DashboardController {
    private final Helpers helpers = new Helpers();
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    @FXML
    private Button logout;
    @FXML
    private Pane paneDashboard, paneTransaction, paneAccount, paneProduct, paneNews, paneSetting;
    private List<Pane> panes;
    @FXML
    private Separator separator;

    @FXML
    public void initialize() {
        panes = List.of(paneDashboard, paneTransaction, paneAccount, paneProduct, paneNews, paneSetting);
        setActivePane(paneDashboard);
    }

    @FXML
    private void handleLogout() {
        switchScene("/com/hcmuss/__admin/fxml/login.fxml", "Login", 800, 600);
    }

    @FXML
    private void dashboard() {
        setActivePane(paneDashboard);
        bp.setCenter(ap);
    }

    @FXML
    private void transaction() {
        navigate(paneTransaction, "transaction");
    }

    @FXML
    private void account() {
        navigate(paneAccount, "account");
    }

    @FXML
    private void product() {
        navigate(paneProduct, "product");
    }

    @FXML
    private void news() {
        navigate(paneNews, "news");
    }

    @FXML
    private void setting() {
        navigate(paneSetting, "setting");
    }

    private void navigate(Pane activePane, String page) {
        setActivePane(activePane);
        loadPage(page);
    }


    @FXML
    private void setActivePane(Pane activePane) {

        panes.forEach(pane -> {
            pane.setStyle("-fx-background-color: transparent");
            for (Node node : pane.getChildren()) {
                if (node instanceof ImageView) {
                    ImageView imageView = (ImageView) node;
                    String oldImageUrl = imageView.getImage().getUrl();
                    if (oldImageUrl != null && !oldImageUrl.contains("-gray")) {
                        String grayImageUrl = oldImageUrl.replace(".png", "-gray.png").replace(".jpg", "-gray.jpg");
                        imageView.setImage(new Image(grayImageUrl));
                    }
                } else if (node instanceof Label) {
                    ((Label) node).setTextFill(javafx.scene.paint.Color.web("#4d4d4d"));
                }
            }
        });

        // Kích hoạt Pane được chọn
        activePane.setStyle("-fx-background-color: #ccc");
        for (Node node : activePane.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                String oldImageUrl = imageView.getImage().getUrl();
                if (oldImageUrl != null && oldImageUrl.contains("-gray")) {
                    String originalImageUrl = oldImageUrl.replace("-gray.png", ".png").replace("-gray.jpg", ".jpg");
                    imageView.setImage(new Image(originalImageUrl));
                }
            } else if (node instanceof Label) {
                ((Label) node).setTextFill(javafx.scene.paint.Color.web("#1307ff"));
            }
        }
    }


    private void loadPage(String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/hcmuss/__admin/fxml/" + page + ".fxml"));
            root.getStylesheets().add(getClass().getResource("/com/hcmuss/__admin/css/" + page + ".css").toExternalForm());
            bp.setCenter(root);
        } catch (IOException e) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void switchScene(String fxmlPath, String title, int width, int height) {
        try {
            Stage stage = (Stage) logout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setScene(new Scene(root, width, height));
            stage.setTitle(title);
            helpers.SwitchScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
