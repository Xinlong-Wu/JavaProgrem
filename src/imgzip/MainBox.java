package imgzip;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashSet;
import java.util.List;


/**
 * @author 乌鑫龙
 */
public class MainBox extends Application {
    /**
     *  静态参数图像计数器和blockList，借此完成图片block的新建及删除
     */
    static int imgCount=0;
    static FlowPane blockList = new FlowPane();

    /**
     *   主界面
     */
    private StackPane centerPane = new StackPane();
    private BorderPane homePane = new BorderPane();
    private Label tipLabelDark = new Label("从本地文件夹拖动图片到这里");
    private Label tipLabel = new Label("从本地文件夹拖动图片到这里");
    private ScrollPane centerScroll = new ScrollPane();

    /**
     *   用来筛选重复添加的图片
     */
    private HashSet<String> imgList = new HashSet<>();

//    private ScrollBar centerScroll = new ScrollBar();



    @Override
    public void start(Stage primaryStage) throws Exception{
        //设置静态值
        blockList.getStyleClass().add("main-box");
        blockList.setBackground(new Background(new BackgroundImage(new Image("res/icon/background.png",1024,700,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        // 设置私有值

        centerScroll.setContent(blockList);
        centerScroll.getStyleClass().addAll("scroll-pane");
        centerScroll.setFitToWidth(true);
        centerScroll.setFitToHeight(true);

        centerPane.getChildren().addAll(centerScroll,tipLabelDark);
        centerPane.getStyleClass().add("center-pane");
        homePane.setCenter(centerPane);
//        centerScroll.setVisible(false)；
//        homePane.setRight(centerScroll);

        // 设置主界面的背景提示，用label实现
        tipLabelDark.getStyleClass().addAll("background-lable");
        tipLabelDark.setAlignment(Pos.BASELINE_CENTER);
        tipLabelDark.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.12),null,null)));
        tipLabelDark.setVisible(false);

        // 临时加载样例图片
        ImgBlock test = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000035.png");
        ImgBlock tet = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000042.jpg");
        ImgBlock tt = new ImgBlock(++imgCount,"E:\\360MoveData\\Users\\fenglinger\\Desktop\\照片\\test\\000040.bmp");

        //临时添加文件进入blockList
        blockList.getChildren().addAll(test,tet,tt);


        //窗口尺寸
        Scene scene = new Scene(homePane, 1024, 700);
        scene.getStylesheets().add("css/imgblock.css");



        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        //  拖动载入文件方法
        centerPane.setOnDragEntered(e->{
            System.out.println("enter");
            tipLabelDark.setVisible(true);
        });
        centerPane.setOnDragExited(e->{
            System.out.println("exist");
            tipLabelDark.setVisible(false);
        });
        centerPane.setOnDragDropped(e->{
            System.out.println("dropped");
            Dragboard dragboard = e.getDragboard();
            List<File> imgs = dragboard.getFiles();
            imgs.forEach(img->{
                String path = img.getPath();
                addToBlockList(path);
                tipLabelDark.setVisible(false);
            });
        });

        //  允许拖放文件（不写这个拖放总不成功，不会写网上查的）
        centerPane.setOnDragOver(e->{
            if (e.getGestureSource() != centerPane
                    && e.getDragboard().hasFiles()) {
                Dragboard dragboard = e.getDragboard();
                List<File> imgs = dragboard.getFiles();
                for (int i = 0;i < imgs.size();i++ ) {
                    String[] path = imgs.get(i).getPath().split("\\.");
                    if(path[path.length-1].equals("png")||path[path.length-1].equals("jpg")||path[path.length-1].equals("bmp")||path[path.length-1].equals("tif")){
                        /* allow for both copying and moving, whatever user chooses */
                        e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }else{
                        e.acceptTransferModes(TransferMode.NONE);
                        break;
                    }
                }

            }
            e.consume();
        });

        // 监听滚动条事件布局会乱掉
//        blockList.layoutYProperty().bind(blockList.heightProperty().divide(100).multiply(centerScroll.valueProperty()).multiply(-1));
    }

    /**
     *  添加block窗格到BlockList(禁止重复插入）
     * @param path
     */
    public void addToBlockList(String path){
        if(imgList.add(path)){
            ImgBlock tmp = new ImgBlock(++imgCount,path);
            blockList.getChildren().add(tmp);
        }
    }

    /**
     *  静态方法删除图片
     * @param index
     */
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
