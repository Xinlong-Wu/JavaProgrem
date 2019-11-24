package imgzip;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 @Author:   肖尧
 @Date: 2019.11.24
 */
public class CreateAccount {
    public CreateAccount(){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateAccount.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create an Account");
            primaryStage.setScene(new Scene(root, 600.0000999999975, 633));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

