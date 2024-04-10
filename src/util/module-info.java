module TH43 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.graphics;

    // If you're using any additional modules, list them here with 'requires'

    // This opens the packages to javafx.graphics module, necessary for reflection
	opens model to javafx.base;
    opens ui to javafx.graphics, javafx.fxml;
    exports ui;
}
