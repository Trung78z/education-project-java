module com.hcmuss.__admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;
    requires java.logging;


    opens com.hcmuss.__admin to javafx.fxml;
    opens com.hcmuss.__admin.controllers to javafx.fxml;
    exports com.hcmuss.__admin;
    exports  com.hcmuss.__admin.controllers;
    exports com.hcmuss.__admin.utils;
    opens com.hcmuss.__admin.utils to javafx.fxml;
}