package com.app.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Scene1Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private void handleSwitchToScene2() {
        // Có thể thêm logic nếu cần
    }
}