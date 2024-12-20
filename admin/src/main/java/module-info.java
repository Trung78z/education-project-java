module com.hcmus.admin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hcmus.admin to javafx.fxml;
    exports com.hcmus.admin;
}