module com.hcmuss.__admin {
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

    opens com.hcmuss.__admin to javafx.fxml;
    opens com.hcmuss.__admin.controllers to javafx.fxml;
    opens com.hcmuss.__admin.models to javafx.base;
    opens com.hcmuss.__admin.utils to javafx.fxml;

    exports com.hcmuss.__admin;
    exports  com.hcmuss.__admin.controllers;
    exports com.hcmuss.__admin.utils;
    exports com.hcmuss.__admin.models;
    exports com.hcmuss.__admin.dtos;
    opens com.hcmuss.__admin.dtos to javafx.fxml;
    uses  com.hcmuss.__admin.Main;
}