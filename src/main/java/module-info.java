module v.sistema_1 {
    requires javafx.controls;
    requires javafx.fxml;

        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires java.datatransfer;
    requires java.desktop;

    opens v.sistema_1 to javafx.fxml;
    exports v.sistema_1;
}