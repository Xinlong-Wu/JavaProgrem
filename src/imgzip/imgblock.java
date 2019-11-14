package imgzip;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * imgblock class
 *      imgblock主体框架
 * @author 乌鑫龙
 * @date 2019/11/12
 */
class ImgBlock extends BorderPane {
    static Image WAITING = new Image("res/icon/waiting.png");
    static Image DONE = new Image("res/icon/done.png");
    static Image DOWNLOAD = new Image("res/icon/download.png");
    static Image CLOSE = new Image("res/icon/close.png");
    static int imgCount = 0;

    private ImageView ivimg = new ImageView();
    private ImageView ivstate = new ImageView(WAITING);
    private ImageView ivDwonLoad = new ImageView(DOWNLOAD);
    private ImageView ivClose = new ImageView(CLOSE);
    private Button btClose = new Button();
    private StackPane cent = new StackPane();
    private String url;
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
    public ImgBlock(String imgUrl){
        //父属性及子属性设定
        this.setPadding(new Insets(10));
        this.setTop(topBar);
        this.setBottom(butBar);
        this.getStyleClass().add("block-bg");
        this.setCenter(cent);
        this.index = imgCount++;
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
        ComboBox<String> trans = new ComboBox<>();
        trans.setPadding(new Insets(0,0,0,10));
        trans.getItems().addAll("To JPG","To PNG","To BMP");
        trans.setValue("To JPG");
        trans.getStyleClass().addAll("block-combo");
        butBar.getChildren().add(trans);





        // 事件响应部分
        save.setOnAction(e->{
            //通过split截取文件路径
            String[] nf=imgUrl.split("\\.");
            System.out.println(nf[0]);
            File f2=new File(imgUrl);
            //使用imgeIO来读取图片
            BufferedImage srcImg = null;
            try {
                srcImg = ImageIO.read(f2);
                if(trans.getValue().equals("To JPG")) {
                    //重新创建图片
                    ImageIO.write(srcImg, "jpg", new File(nf[0] + ".jpg"));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ivstate.setImage(DONE);
        });

    }
}
