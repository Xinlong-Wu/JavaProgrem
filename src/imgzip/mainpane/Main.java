package imgzip.mainpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 @Author:  吴泳仪
 @Date: 2019.12.8
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
        	Parent root=FXMLLoader.load(getClass().getResource("/fxml/Pane_sceen.fxml"));
            primaryStage.setTitle("Homepage");
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
