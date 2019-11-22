package imgzip;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 @Author:   肖尧
 @Date: 2019.11.22
 */

public class WrongPassword {
    public WrongPassword(){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/WrongPassword.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Password is wrong!");
            primaryStage.setScene(new Scene(root, 638.0, 450.0));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
