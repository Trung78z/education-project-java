package com.hcmuss.__admin.controllers;

import com.hcmuss.__admin.utils.Helpers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController {
    private Helpers helpers = new Helpers();

    @FXML
    private Button logout;

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
        System.out.println("dashboard");
        loadPage("dashboard");
    }

    @FXML
    protected void transaction() {
        System.out.println("transaction");
        loadPage("transaction");
    }

    @FXML
    protected void account() {
        System.out.println("account");
        loadPage("account");
    }

    @FXML
    protected void product() {
        System.out.println("product");
        loadPage("product");
    }

    @FXML
    protected void news() {
        System.out.println("news");
        loadPage("news");
    }

    @FXML
    protected void setting() {
        System.out.println("setting");
        loadPage("setting");

    }


    private void loadPage(String page) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hcmuss/__admin/fxml/" + page + ".fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) someNode.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error loading page", e);
        }
    }
}
