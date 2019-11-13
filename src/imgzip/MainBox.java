package imgzip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainBox extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Scene scene = new Scene(new ImgBlock(),300,380);
        ImgBlock test = new ImgBlock("E:\\360MoveData\\Users\\fenglinger\\Desktop\\20191019233718.jpg");
        FlowPane test1 = new FlowPane();
        test1.getChildren().addAll(test);
        test1.getStyleClass().add("main-box");

        //真正尺寸
        Scene scene = new Scene(test1, 1024, 700);
        scene.getStylesheets().add("css/imgblock.css");

        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
