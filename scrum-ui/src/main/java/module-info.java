module com.group_srinivasan.scrumui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.group_srinivasan.scrumui to javafx.fxml;
    exports com.group_srinivasan.scrumui;
}