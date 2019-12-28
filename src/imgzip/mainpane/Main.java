package imgzip.mainpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 @Author:  吴泳仪
 @Date: 2019.12.8
 */
public class Main extends Application {
    /**
    开启主页面
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
        	Parent root=FXMLLoader.load(getClass().getResource("/fxml/Pane_sceen.fxml"));
            primaryStage.setTitle("Homepage");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.getIcons().add(new Image("res/icon/logo.png"));
            primaryStage.show();
            primaryStage.getIcons().add(new Image("res/icon/logo.ico"));

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
