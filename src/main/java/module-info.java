module com.example.imgoinginsanehelp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.imgoinginsanehelp to javafx.fxml;
    exports com.example.imgoinginsanehelp;
}