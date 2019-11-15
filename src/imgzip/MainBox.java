package imgzip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * @author 乌鑫龙
 */
public class MainBox extends Application {
    static int imgCount=0;
    static FlowPane blockList = new FlowPane();


    private Label tipLabel = new Label("从本地文件夹拖动图片到这里");
    private StackPane homePane = new StackPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        tipLabel.getStyleClass().addAll("background-lable");
        homePane.getChildren().addAll(tipLabel,blockList);


        ImgBlock test = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000035.png");
        ImgBlock tet = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000042.jpg");
        ImgBlock tt = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000040.bmp");

        blockList.getChildren().addAll(test,tet,tt);
        blockList.getStyleClass().add("main-box");

        //真正尺寸
        Scene scene = new Scene(homePane, 1024, 700);
        scene.getStylesheets().add("css/imgblock.css");



        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    static void drop(int index){
        if(imgCount>1){
            blockList.getChildren().remove(index);
            imgCount--;
        }
        else {
            blockList.getChildren().clear();
            imgCount--;
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
