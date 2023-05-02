module com.mycompany.sportifyfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens com.mycompany.sportifyfx to javafx.fxml;
    exports com.mycompany.sportifyfx;
    exports com.mycompany.sportifyfx.data;
    exports com.mycompany.sportifyfx.Model;


}
