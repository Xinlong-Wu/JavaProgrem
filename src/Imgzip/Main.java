package Imgzip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(new ImgBlock(),300,380);
//        Scene scene = new Scene(new ImgBlock(), 1024, 700);     //真正尺寸

        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
