package com.hcmuss.__admin.utils;

import javafx.stage.Stage;

import java.awt.*;

public class Helpers {

    public void SwitchScene(Stage stage) {

        double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        double stageWidth = 1280;
        double stageHeight = 768;
        stage.setX((screenWidth - stageWidth) / 2);
        stage.setY((screenHeight - stageHeight) / 2);
    }
}
