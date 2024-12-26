package com.hcmuss.__admin.controllers;

import com.hcmuss.__admin.utils.Helpers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController {
    private Helpers helpers = new Helpers();
    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;
    @FXML
    private Button logout;
    @FXML
    private Pane pane;

    @FXML
    private Text title;

    @FXML
    private HBox navbar;

    @FXML
    private VBox sidebar;

    @FXML
    protected void handleLogout() {
        try {
            FXMLLoader fxmlLoaderDashboard = new FXMLLoader(getClass().getResource("/com/hcmuss/__admin/fxml/login.fxml"));

            Scene loginScene = new Scene(fxmlLoaderDashboard.load(), 800, 600);
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
            helpers.SwitchScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void dashboard() {
        bp.setCenter(ap);
    }

    @FXML
    protected void transaction() {
        loadPage("transaction");
    }

    @FXML
    protected void account() {
        loadPage("account");
    }

    @FXML
    protected void product() {
        loadPage("product");
    }

    @FXML
    protected void news() {
        loadPage("news");
    }

    @FXML
    protected void setting() {
        loadPage("setting");

    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/hcmuss/__admin/fxml/" + page + ".fxml"));
        } catch (Exception e) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, e);

        }
        bp.setCenter(root);

    }
}
