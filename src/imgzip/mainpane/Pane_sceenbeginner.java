package imgzip.mainpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pane_sceenbeginner {

    public Pane_sceenbeginner(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pane_sceen.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Homepage");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
