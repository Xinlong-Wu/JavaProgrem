package imgzip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author 乌鑫龙
 */
public class MainBox extends Application {
    static FlowPane mainPane = new FlowPane();
    static int imgCount = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{

//        Scene scene = new Scene(new ImgBlock(),300,380);
        ImgBlock test = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000035.png");
        ImgBlock tet = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000042.jpg");
        ImgBlock tt = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000040.bmp");

        mainPane.getChildren().addAll(test,tet,tt);
        mainPane.getStyleClass().add("main-box");

        //真正尺寸
        Scene scene = new Scene(mainPane, 1024, 700);
        scene.getStylesheets().add("css/imgblock.css");



        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    static void drop(int index){
        if(imgCount>1){
            mainPane.getChildren().remove(index);
            imgCount--;
        }
        else {
            mainPane.getChildren().clear();
            imgCount--;
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
