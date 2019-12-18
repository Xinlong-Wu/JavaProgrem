package imgzip.mainpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 @Author:  吴泳仪
 @Date: 2019.12.9
 */
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
    public Pricepane(int a){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pricepane(none).fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Price");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
