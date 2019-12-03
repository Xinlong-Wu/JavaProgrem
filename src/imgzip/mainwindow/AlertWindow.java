package imgzip.mainwindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox(10);
        Scene scene = new Scene(pane,360,210);
        primaryStage.setScene(scene);
        primaryStage.setTitle("关于imgZip");
        primaryStage.show();
    }
}
