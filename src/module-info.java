module demo.school.managment.system {
    requires javafx.fxml;
    requires javafx.controls;
    requires mysql.connector.java;
    requires java.sql;
    exports school to javafx.graphics;
    exports  school.views.level to javafx.fxml;
    exports  school.views.teacher to javafx.fxml;

    opens school.views;
    opens school.views.level;
    opens school.views.teacher;
}