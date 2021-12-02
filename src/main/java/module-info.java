module pass_manager.password_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;


    opens pass_manager.password_manager to javafx.fxml;
    exports pass_manager.password_manager;
}