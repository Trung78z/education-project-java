module com.hcmuss.admin {
    requires javafx.fxml;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires fontawesomefx;
    requires org.controlsfx.controls;
    requires java.logging;
    requires javafx.web;
    requires java.net.http;
    requires java.desktop;
    requires java.prefs;
    requires io.github.cdimascio.dotenv.java;

    opens com.hcmuss.admin to javafx.fxml;
    opens com.hcmuss.admin.controllers to javafx.fxml;
    opens com.hcmuss.admin.models to javafx.base;
    opens com.hcmuss.admin.utils to javafx.fxml;

    exports com.hcmuss.admin;
    exports  com.hcmuss.admin.controllers;
    exports com.hcmuss.admin.utils;
    exports com.hcmuss.admin.models;
    exports com.hcmuss.admin.dtos;
    opens com.hcmuss.admin.dtos to javafx.fxml;
    exports com.hcmuss.admin.models.product;
    opens com.hcmuss.admin.models.product to javafx.base;
    uses  com.hcmuss.admin.Main;
}