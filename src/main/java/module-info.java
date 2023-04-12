module com.example.addressapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.addressapp to javafx.fxml;
    exports com.example.addressapp;
}