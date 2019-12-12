package mainpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pricepane  extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root=FXMLLoader.load(getClass().getResource("Pricepane.fxml"));
            primaryStage.setTitle("Pricepane");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
