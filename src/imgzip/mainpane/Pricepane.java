package imgzip.mainpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Pricepane{
    public Pricepane(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pricepane.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Price");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
