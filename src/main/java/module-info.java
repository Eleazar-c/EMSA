module com.proyect.emsa {
    requires javafx.controls;
    exports com.proyect.emsa;
    //AGREGADO
     requires java.sql;
    requires java.base;
    exports controller;
    requires javafx.fxml;
    opens controller to javafx.fxml;
    opens controller.usuario to javafx.fxml;
    opens controller.eventos to javafx.fxml;
    opens controller.home to javafx.fxml;
    opens controller.login to javafx.fxml;


    opens clases to javafx.base;
}
