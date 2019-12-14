package imgzip.mainpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Functionpage {
    public Functionpage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Functionpage.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Price");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
