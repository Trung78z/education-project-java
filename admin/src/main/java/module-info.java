module main.admin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens main.admin to javafx.fxml;
    exports main.admin;
    exports main.admin.controllers;
    opens main.admin.controllers to javafx.fxml;
}