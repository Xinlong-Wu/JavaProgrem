package imgzip.LoginSignIn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FindPassword {

    public FindPassword(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FindPassword.fxml"));


            Button verify = (Button)root.lookup("#verify");
            verify.setDisable(true);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 607, 500));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
