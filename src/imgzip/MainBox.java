package imgzip;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


/**
 * @author 乌鑫龙
 */
public class MainBox extends Application {
    static int IMGCOUNT=0;
    static FlowPane blockList = new FlowPane();


    private Label tipLabel = new Label("从本地文件夹拖动图片到这里");
    private Label tipLabelDark = new Label("从本地文件夹拖动图片到这里");
    private StackPane centerPane = new StackPane();
    private BorderPane homePane = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        //主界面装载所有的的节点
        centerPane.getChildren().addAll(tipLabel,blockList,tipLabelDark);
        homePane.setCenter(centerPane);


        // 设置主界面的背景提示，用label实现
        tipLabel.getStyleClass().addAll("background-lable");
        tipLabelDark.getStyleClass().addAll("background-lable");
        tipLabel.setAlignment(Pos.BASELINE_CENTER);
        tipLabelDark.setAlignment(Pos.BASELINE_CENTER);
        tipLabelDark.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.12),null,null)));
        tipLabelDark.setVisible(false);


        // 临时加载样例图片
        ImgBlock test = new ImgBlock("E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000035.png");
        ImgBlock tet = new ImgBlock("E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000042.jpg");
        ImgBlock tt = new ImgBlock("E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000040.bmp");

        //临时添加文件进入blockList
        blockList.getChildren().addAll(test,tet,tt);
        blockList.getStyleClass().add("main-box");

        //窗口尺寸
        Scene scene = new Scene(homePane, 1024, 700);
        scene.getStylesheets().add("css/imgblock.css");



        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        centerPane.setOnDragEntered(e->{
            System.out.println("enter");
            tipLabelDark.setVisible(true);
        });
        centerPane.setOnDragExited(e->{
            System.out.println("exist");
            tipLabelDark.setVisible(false);
        });
        centerPane.setOnDragOver(e->{
            if (e.getGestureSource() != centerPane
                    && e.getDragboard().hasFiles()) {
                /* allow for both copying and moving, whatever user chooses */
                e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            e.consume();
        });
        tipLabelDark.setOnDragDropped(e->{
            System.out.println("dropped");
            Dragboard dragboard = e.getDragboard();
            List<File> imgs = dragboard.getFiles();
//            imgs.get(0).getPath()
            imgs.forEach(img->{
                String path = img.getPath();
                ImgBlock tmp = new ImgBlock(path);
                blockList.getChildren().add(tmp);
                tipLabelDark.setVisible(false);
            });
        });
    }



    static void drop(int index){
        if(IMGCOUNT>1){
            blockList.getChildren().remove(index);
            IMGCOUNT--;
        }
        else {
            blockList.getChildren().clear();
            IMGCOUNT--;
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
