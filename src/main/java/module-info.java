module com.example.addressapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;


    opens com.example.addressapp to javafx.fxml;
    exports com.example.addressapp;
}