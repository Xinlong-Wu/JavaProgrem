package imgzip.Login_SignIn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.control.Label;

/**
 @Author:   肖尧
 @Date: 2019.12.4
 */
public class CreateAccount {
    public CreateAccount(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateAccount.fxml"));

            Label already = (Label)root.lookup("#Al");
            already.setVisible(false);

            Label already2 = (Label)root.lookup("#Al2");
            already2.setVisible(false);

            Button createAccount = (Button)root.lookup("#createAccount");
            createAccount.setDisable(true);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create an Account");
            primaryStage.setScene(new Scene(root, 600.0000999999975, 633));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

class CreateSuccessfully{

    public CreateSuccessfully(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateSuccessfully.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create an Account Successfully!");
            primaryStage.setScene(new Scene(root, 638, 406));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}

