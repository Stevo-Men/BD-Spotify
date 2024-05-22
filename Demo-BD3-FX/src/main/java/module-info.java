module com.example.demobd3fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demobd3fx.models to javafx.base;
    opens com.example.demobd3fx.viewModels to javafx.base;
    exports com.example.demobd3fx;
    exports com.example.demobd3fx.viewModels;
    opens com.example.demobd3fx to javafx.base, javafx.fxml;

}