package imgzip.mainwindow;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.File;
import java.util.HashSet;
import java.util.List;


/**
 * @author 乌鑫龙
 */
public class MainBox extends Scene {
    /**
     *  静态参数图像计数器和blockList，借此完成图片block的新建及删除
     */
    static Image SAVE = new Image("res/icon/save.png");
    static Image ADD = new Image("res/icon/add.png");
    static Image CLEAR = new Image("res/icon/clear.png");
    static Image SAVED = new Image("res/icon/save-dark.png");
    static Image ADDD = new Image("res/icon/add-dark.png");
    static Image CLEARD = new Image("res/icon/clear-dark.png");
    static int imgCount=0;
    static FlowPane blockList = new FlowPane();
    static BorderPane homePane = new BorderPane();
    /**
     *   用来筛选重复添加的图片
     */
    static HashSet<String> imgList = new HashSet<>();

    /**
     *   主界面 和 顶部栏
     */
    private StackPane centerPane = new StackPane();
    private VBox topPane = new VBox();
    private Label tipLabelDark = new Label("从本地文件夹拖动图片到这里");
    private ScrollPane centerScroll = new ScrollPane();
    private ImageView ivSave = new ImageView(SAVE);
    private ImageView ivAdd = new ImageView(ADD);
    private ImageView ivClear = new ImageView(CLEAR);

    /**
     * 将主界面整合成一个Scene类，方便调用
     *
     *  root   静态homePane，因为只用一次所以就设为静态了
     *  width  1024
     *  height 800
     *
     */
    public MainBox() {
        //窗口尺寸
        super(homePane, 1024, 800);
        this.getStylesheets().add("css/imgblock.css");

        //设置静态值
        blockList.getStyleClass().add("main-box");
        blockList.setBackground(new Background(new BackgroundImage(new Image("res/icon/background.png",1024,700,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        // 设置私有值

        centerScroll.setContent(blockList);
        centerScroll.getStyleClass().addAll("scroll-pane");
        centerScroll.setFitToWidth(true);
        centerScroll.setFitToHeight(true);

        centerPane.getChildren().addAll(centerScroll,tipLabelDark);
        centerPane.getStyleClass().add("center-pane");
        homePane.setCenter(centerPane);

        // 顶部组件
        // 菜单栏
        MenuItem about = new MenuItem("关于 imagine");
        MenuItem exit = new MenuItem("退出");
        Menu imagine = new Menu("imagine");
        imagine.getItems().addAll(about,exit);

        MenuItem open = new MenuItem("打开  (Ctrk+O)");
        Menu file = new Menu("文件");
        file.getItems().addAll(open);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(imagine,file);

        // 控制栏
        Button btAdd = new Button();
        btAdd.setGraphic(ivAdd);
        btAdd.getStyleClass().addAll("top-ctrl-bt");
        Button btSave = new Button();
        btSave.setGraphic(ivSave);
        btSave.getStyleClass().addAll("top-ctrl-bt");
        Button btClear = new Button();
        btClear.setGraphic(ivClear);
        btClear.getStyleClass().addAll("top-ctrl-bt");
        HBox ctrlBar = new HBox(20);
        ctrlBar.getChildren().addAll(btAdd,btSave,btClear);
        ctrlBar.getStyleClass().addAll("top-ctrl");
        ctrlBar.setPadding(new Insets(0,0,0,20));
        topPane.getChildren().addAll(menuBar,ctrlBar);
        topPane.getStyleClass().addAll("top-pane");
        homePane.setTop(topPane);

        // 设置主界面的背景提示，用label实现
        tipLabelDark.getStyleClass().addAll("background-lable");
        tipLabelDark.setAlignment(Pos.BASELINE_CENTER);
        tipLabelDark.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.12),null,null)));
        tipLabelDark.setVisible(false);

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
                    String type = path[path.length-1].toLowerCase();
                    if("png".equals(type)|| "jpg".equals(type)|| "bmp".equals(type)|| "tif".equals(type)){
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

        // 清空blockList
        btClear.setOnMouseEntered(e->{
            setImg(ivClear,CLEARD);
            e.consume();
        });
        btClear.setOnMouseExited(e->{
            setImg(ivClear,CLEAR);
            e.consume();
        });
        btClear.setOnAction(e->{
            dropAll();
        });

        // 从文件管理器打开图像
        btAdd.setOnMouseEntered(e->{
            setImg(ivAdd,ADDD);
            e.consume();
        });
        btAdd.setOnMouseExited(e->{
            setImg(ivAdd,ADD);
            e.consume();
        });
        btAdd.setOnAction(e->{
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择图片");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("全部图片（.bmp/.png/.jpg/.tif", "*.bmp","*.jpg","*.png","*.tif"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("TIF", "*.tif"));
            try{
                List list = fileChooser.showOpenMultipleDialog(stage);
                list.forEach(li->{
                    String path = li.toString();
                    System.out.println(path);
                    addToBlockList(path);
                });
            }
            catch (Exception ex){
                System.out.println("MainBos->btAdd->action->list: "+ex);
            }
            //销毁实例
            e.consume();
        });


        btSave.setOnMouseEntered(e->{
            setImg(ivSave,SAVED);
            e.consume();
        });
        btSave.setOnMouseExited(e->{
            setImg(ivSave,SAVE);
            e.consume();
        });
        btSave.setOnAction(e->{
            Stage stage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择文件目录");
            int coun = 0;
            try{
                String path = directoryChooser.showDialog(stage).getPath();
                System.out.println(path);
                ObservableList i = blockList.getChildren();
                for(int j = 0;j<i.size();j++){
                    ((ImgBlock)i.get(j)).setState(ImgBlock.LOADING);
                    ((ImgBlock)i.get(j)).saveImg(path+"\\"+j);
                }
            }
            catch (IIOException ex){
                System.out.println("MainBos->btAdd->action->file: "+ex.getMessage());
            }
            catch (NullPointerException ex){
                System.out.println("MainBos->btAdd->action->list: "+ex);
            }
        });

    }

    /**
     *  修改ImageView中的图片
     * @param iv，im
     */
    void setImg(ImageView iv,Image im){
        iv.setImage(im);
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
     * @param block
     */
    static void drop(ImgBlock block){
        String url = block.getUrl();
        if(imgCount>1){
            imgList.remove(url);
            blockList.getChildren().remove(block);
            imgCount--;
        }
        else {
            imgList.clear();
            blockList.getChildren().clear();
            imgCount--;
        }
    }

    /**
     * 删除全部图片
     */
    private void dropAll(){
        blockList.getChildren().clear();
        imgList.clear();
        imgCount=0;
    }
}