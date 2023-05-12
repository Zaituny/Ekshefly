module com.example.eksheflyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.eksheflyproject to javafx.fxml;
    exports com.example.eksheflyproject;
}