package imgzip.mainwindow;


import imgzip.alertwindow.AlertButton;
import imgzip.alertwindow.AlertWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * imgblock class
 *      imgblock主体框架
 * @author 乌鑫龙
 * @date 2019/11/12
 */
class ImgBlock extends BorderPane {
    /**
     *  静态参数加载各种资源图像
      */
    static Image WAITING = new Image("res/icon/waiting.png");
    static Image LOADING = new Image("res/icon/loading.gif");
    static Image DONE = new Image("res/icon/done.png");
    static Image DOWNLOAD = new Image("res/icon/download.png");
    static Image UPLOAD = new Image("res/icon/upload.png");
    static Image CLOSE = new Image("res/icon/close.png");
    static String JPG = "To JPG";
    static String PNG = "To PNG";
    static String BMP = "To BMP";
    static ExecutorService saverPool = Executors.newCachedThreadPool();
    static ExecutorService zipPool = Executors.newCachedThreadPool();
    static ExecutorService uploadPool = Executors.newCachedThreadPool();

    /**
     *  私有参数用于各个类特定的值、和对象，用来组成
     */
    private ImageView ivimg = new ImageView();
    private ImageView ivstate = new ImageView(WAITING);
    private ImageView ivDwonLoad = new ImageView(DOWNLOAD);
    private ImageView ivClose = new ImageView(CLOSE);
    private ImageView ivUpload = new ImageView(UPLOAD);
    private Button btClose = new Button();
    private StackPane cent = new StackPane();
    private ComboBox<String> trans = new ComboBox<>();
    private String url;
    private String dialog = "";
    private Label size;
    private int index;
    private Boolean isUpload = false;
    HBox topBar = new HBox();
    VBox botBar = new VBox();
    HBox transBar = new HBox();
    HBox sliderBox = new HBox();
    Slider sliderBar = new Slider(0,100,100);


//    Menu
    /**
     *
     * 图片预览框 构造方法
     *
     */
    public ImgBlock(int imgCount,String imgUrl){
        //父属性及子属性设定
        this.setPadding(new Insets(10));
        this.setTop(topBar);
        this.setBottom(botBar);
        this.getStyleClass().add("block-bg");
        this.setCenter(cent);
        this.index = imgCount;
        this.url = imgUrl;

        // 边框阴影设置
        DropShadow dropShadow =new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        dropShadow.setSpread(0.0);
        this.setEffect(dropShadow);


        //私有属性设定
        Image tmp = new Image("file:"+imgUrl);
        ivimg.setImage(tmp);
        ivimg.setPreserveRatio(true);
        if(tmp.getHeight()>tmp.getWidth()){
            ivimg.setFitHeight(205);
        }else{
            ivimg.setFitWidth(225);
        }
        ivstate.setFitHeight(15);
        ivstate.setFitWidth(15);
        ivDwonLoad.setFitHeight(15);
        ivDwonLoad.setFitWidth(15);
        ivClose.setFitHeight(20);
        ivClose.setFitWidth(20);
        ivUpload.setFitHeight(15);
        ivUpload.setFitWidth(15);

        Label lbZip = new Label("压缩：");
        Label lbZipPre = new Label("100%");
        lbZipPre.setPrefWidth(40);
        sliderBox.getChildren().addAll(lbZip,sliderBar,lbZipPre);
        sliderBox.getStyleClass().addAll("slidebox");

        Label size = new Label(this.getFileSize(imgUrl));
        size.getStyleClass().addAll("size-Label");
        Label split = new Label("/");
        Label dialog = new Label(size.getText());
        dialog.getStyleClass().addAll("size-Label");
        HBox sizeBox = new HBox();
        sizeBox.getChildren().addAll(dialog,split,size);
//        sizeBox.setAlignment(Pos.BOTTOM_RIGHT);
        sizeBox.getStyleClass().addAll("sizeBox");


        //关闭按钮设定
        btClose.setGraphic(ivClose);
        btClose.getStyleClass().add("block-box-close");
        HBox btBox = new HBox();
        btBox.getStyleClass().add("block-box");
        btBox.getChildren().add(btClose);

        // 顶部栏属性设定
        topBar.getStyleClass().setAll("block-topbar");
        topBar.setPadding(new Insets(0,0,0,10));

        //中间图片位置
        cent.getStyleClass().addAll("block-imgbox");
        cent.getChildren().addAll(ivimg);

        //底部栏属性设定
        botBar.getChildren().addAll(sliderBox,transBar);
        botBar.getStyleClass().setAll("block-botbar");
//        botBar.setPadding(new Insets(0,0,0,10));

        //保存菜单设定
        MenuButton downLoad = new MenuButton();
        downLoad.setPadding(new Insets(0,0,0,10));
        MenuItem save = new MenuItem("保存");
        MenuItem saveAs = new MenuItem("另存为");
        save.getStyleClass().addAll("block-menu-basic");
        saveAs.getStyleClass().addAll("block-menu-basic");
        downLoad.getItems().add(save);
        downLoad.getItems().add(saveAs);
        downLoad.setGraphic(ivDwonLoad);
        downLoad.getStyleClass().addAll("block-menu-bt");

        //上传按钮
        Button btUpload = new Button();
        btUpload.setPadding(new Insets(0,0,0,10));
        btUpload.setGraphic(ivUpload);
        btUpload.getStyleClass().addAll("block-menu-bt");

        // 状态及关闭图标设定
        topBar.getChildren().add(ivstate);
        topBar.getChildren().add(downLoad);
        topBar.getChildren().add(btUpload);
        topBar.getChildren().add(btBox);


        //底部栏设定


        sliderBar.getStyleClass().addAll("slidebar");

        trans.setPadding(new Insets(0,0,0,10));
        trans.getItems().addAll(JPG,PNG,BMP);
        trans.getStyleClass().addAll("block-combo");
        transBar.getChildren().addAll(trans, sizeBox);
        transBar.getStyleClass().addAll("transbar");
        String type = imgUrl.split("\\.")[1].toLowerCase();
        if("jpg".equals(type)){
            trans.setValue(JPG);
        }
        else if("png".equals(type)){
            trans.setValue(PNG);
        }
        else if("bmp".equals(type)){
            trans.setValue(BMP);
        }



        // 事件响应部分
        trans.setOnAction(e->{
            ivstate.setImage(WAITING);
            //销毁实例
            e.consume();
        });

        // 保存
        save.setOnAction(e->{
            ivstate.setImage(LOADING);
            try {
                saveImg(imgUrl);
            }
            catch (IIOException ex){
                System.out.println("Imgblock->save->action: "+ex.getMessage());
            }
            finally {
                //销毁实例
                e.consume();
            }
        });

        // 另存为
        saveAs.setOnAction(e->{
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择保存路径");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("全部图片（.bmp/.png/.jpg)", "*.bmp","*.jpg","*.png"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"));
            try{
                String path = fileChooser.showSaveDialog(stage).getPath();
                System.out.println(path);
                ivstate.setImage(LOADING);
                saveImg(path);
            }
            catch (IIOException ex){
                System.out.println("Imgblock->save->action: "+ex.getMessage());
            }
            catch (NullPointerException ex){
                System.out.println("imgBlock->saveAs->action->fileChooser: "+ex);
            }
            finally {
                //销毁实例
                e.consume();
            }
        });

        // 上传
        btUpload.setOnAction(e->{
            //检查登录！！！！
            uploadImg();
            //销毁实例
            e.consume();
        });

        //关闭按钮
        btClose.setOnAction(e->{
            MainBox.drop(this);
            //销毁实例
            e.consume();
        });


        /**
         *  图片压缩监听
         */
        sliderBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lbZipPre.setText(String.format("%.0f",sliderBar.getValue())+"%");
                System.out.println(sliderBar.getValue());
            }
        });

        sliderBar.setOnMouseReleased(event ->{
            this.setState(LOADING);
            zipImg(sliderBar.getValue());
            event.consume();
        });

    }

    void setState(Image state){
        ivstate.setImage(state);
    }

    public ImageView getIvstate() {
        return ivstate;
    }

    public ComboBox<String> getTrans() {
        return trans;
    }

    public Boolean getUpload() {
        return isUpload;
    }

    public void setUpload(Boolean upload) {
        isUpload = upload;
    }

    /**
     * 保存图片的储存路径方法
     * @param path
     */
    void saveImg(String path) throws IIOException{
            saverPool.execute(new SaveImg(this,path));
    }

    /**
     * 压缩图片方法
     * @param rate
     */
    void zipImg(double rate){
        zipPool.execute(new ZipImg(this,rate));
    }

    /**
     * 图片上传方法
     */
    void uploadImg(){
        ivstate.setImage(LOADING);
        if(!isUpload){
            ivstate.setImage(DONE);
            return;
        }
        try {
            uploadPool.execute(new UploadImg(this));
        } catch (IIOException e) {
            AlertWindow alertWindow = new AlertWindow("上传失败",e.getMessage());
        }
    }
    void uploadImg(String uuid){
        ivstate.setImage(LOADING);
        if(!isUpload){
            ivstate.setImage(DONE);
            return;
        }
        try {
            (new UploadImg(this,uuid)).run();
        } catch (IIOException e) {
            AlertWindow alertWindow = new AlertWindow("上传失败",e.getMessage());
            ivstate.setImage(WAITING);
        }
    }

    /**
     *  图片url访问器
     * @return
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * 图像序号访问器
     * @return
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * 获取文件大小
     * @param url
     */
    String getFileSize(String url){
        String str = "null";
        try {
            FileImageInputStream fiis = new FileImageInputStream(new File(url));
            Float fsize = (float) fiis.length() / 1024;
            if(fsize<100){
                str = String.format("%.2f",fsize) + "KB";
            }
            else {
                str = String.format("%.2f",fsize/1024) + "MB";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


}

