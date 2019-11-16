package imgzip;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




/**
 * imgblock class
 *      imgblock主体框架
 * @author 乌鑫龙
 * @date 2019/11/12
 */
class ImgBlock extends BorderPane {
    //  静态参数加载各种资源图像
    static Image WAITING = new Image("res/icon/waiting.png");
    static Image LOADING = new Image("res/icon/loading.gif");
    static Image DONE = new Image("res/icon/done.png");
    static Image DOWNLOAD = new Image("res/icon/download.png");
    static Image CLOSE = new Image("res/icon/close.png");
    static String JPG = "To JPG";
    static String PNG = "To PNG";
    static String BMP = "To BMP";
    static String TIF = "To TIF";


    private ImageView ivimg = new ImageView();
    private ImageView ivstate = new ImageView(WAITING);
    private ImageView ivDwonLoad = new ImageView(DOWNLOAD);
    private ImageView ivClose = new ImageView(CLOSE);
    private Button btClose = new Button();
    private StackPane cent = new StackPane();
    private ComboBox<String> trans = new ComboBox<>();
    private ExecutorService saverPool = Executors.newCachedThreadPool();
    private String url;
    private String Dialog = "";
    private Label size;
    private int index;
    HBox topBar = new HBox();
    HBox butBar = new HBox();


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
        this.setBottom(butBar);
        this.getStyleClass().add("block-bg");
        this.setCenter(cent);
        this.index = imgCount;
        url = imgUrl;

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
            ivimg.setFitHeight(200);
        }else{
            ivimg.setFitWidth(225);
        }
        ivstate.setFitHeight(15);
        ivstate.setFitWidth(15);
        ivDwonLoad.setFitHeight(15);
        ivDwonLoad.setFitWidth(15);
        ivClose.setFitHeight(20);
        ivClose.setFitWidth(20);
//        String[] tmpUrl = imgUrl.split("\\\\");
//        Dialog = tmpUrl[0];
//        for(int i = 1;i<tmpUrl.length-1;i++){
//            Dialog+="\\\\"+tmpUrl[i];
//        }


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
        butBar.getStyleClass().setAll("block-butbar");
        butBar.setPadding(new Insets(0,0,0,10));

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


        // 状态及关闭图标设定
        topBar.getChildren().add(ivstate);
        topBar.getChildren().add(downLoad);
        topBar.getChildren().add(btBox);


        //底部栏设定
        trans.setPadding(new Insets(0,0,0,10));
        trans.getItems().addAll(JPG,PNG,BMP,TIF);
        trans.getStyleClass().addAll("block-combo");
        butBar.getChildren().add(trans);
        if(imgUrl.split("\\.")[1].equals("jpg")){
            trans.setValue(JPG);
        }
        else if(imgUrl.split("\\.")[1].equals("png")){
            trans.setValue(PNG);
        }
        else if(imgUrl.split("\\.")[1].equals("bmp")){
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
            saverPool.execute(new SaveImg(imgUrl,imgUrl));
            //销毁实例
            e.consume();
        });

        // 另存为
        saveAs.setOnAction(e->{
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择保存路径");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("TIF", "*.tif"));
            String path = fileChooser.showSaveDialog(stage).getPath();
            System.out.println(path);
            ivstate.setImage(LOADING);
            saverPool.execute(new SaveImg(imgUrl,path));
            //销毁实例
            e.consume();
        });

        btClose.setOnAction(e->{
            MainBox.drop(index);
            //销毁实例
            e.consume();
        });
    }


    /**
     *   保存文件的线程
     */
    class SaveImg implements Runnable{
        String imgUrl;
        String[] newUrl;
        public SaveImg(String url,String newUrl){
            imgUrl = url;
            this.newUrl = newUrl.split("\\.");
        }
        @Override
        public void run() {
            //通过split截取文件路径
            String[] url=imgUrl.split("\\.");
            System.out.println(url[0]);
            File f2=new File(imgUrl);
            //使用imgeIO来读取图片
            BufferedImage srcImg = null;
            try {
                srcImg = ImageIO.read(f2);
                if(trans.getValue().equals(JPG)) {
                    //重新创建图片
                    BufferedImage newBufferedImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                    newBufferedImage.createGraphics().drawImage(srcImg, 0, 0, java.awt.Color.WHITE, null);
                    ImageIO.write(newBufferedImage, "jpg", new File(newUrl[0] + ".jpg"));
                }
                else if(trans.getValue().equals(PNG)) {
                    //重新创建图片
                    BufferedImage newBufferedImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                    newBufferedImage.createGraphics().drawImage(srcImg, 0, 0, java.awt.Color.WHITE, null);
                    ImageIO.write(srcImg, "jpg", new File(newUrl[0] + ".png"));
                }
                else if(trans.getValue().equals(TIF)) {
                    //重新创建图片
                    BufferedImage newBufferedImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                    newBufferedImage.createGraphics().drawImage(srcImg, 0, 0, java.awt.Color.WHITE, null);
                    ImageIO.write(srcImg, "jpg", new File(newUrl[0] + ".tif"));
                }
                else if(trans.getValue().equals(BMP)) {

                    //重新创建图片(使用了awt包）
                    int h = srcImg.getHeight(), w = srcImg.getWidth();
                    int[] pixel = new int[w * h];
                    PixelGrabber pixelGrabber = new PixelGrabber(srcImg, 0, 0, w, h, pixel, 0, w);
                    pixelGrabber.grabPixels();
                    MemoryImageSource m = new MemoryImageSource(w, h, pixel, 0, w);
                    java.awt.Image image =  Toolkit.getDefaultToolkit().createImage(m);
                    BufferedImage buff = new BufferedImage(w, h, BufferedImage.TYPE_USHORT_565_RGB);
                    buff.createGraphics().drawImage(image, 0, 0 ,null);
                    ImageIO.write(buff, "bmp", new File(newUrl[0] + ".bmp"));
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
            ivstate.setImage(DONE);
        }
    }
}

