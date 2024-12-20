module com.hcmuss.__admin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hcmuss.__admin to javafx.fxml;
    exports com.hcmuss.__admin;
}