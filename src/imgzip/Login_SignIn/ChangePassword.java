package imgzip.Login_SignIn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ChangePassword {


    public ChangePassword(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChangePassword.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 607, 500));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

class ChangePasswordSuccessfully{

    public ChangePasswordSuccessfully(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChangePasswordSuccessfully.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 607, 500));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
