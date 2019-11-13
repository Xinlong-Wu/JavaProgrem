package imgzip;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


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

    private ImageView ivimg = null;
    private ImageView ivstate = new ImageView(WAITING);
    private ImageView ivDwonLoad = new ImageView(DOWNLOAD);
    private ImageView ivClose = new ImageView(CLOSE);
    private Button btClose = new Button();
    HBox topBar = new HBox();
    HBox butBar = new HBox();

//    Menu
    /**
     *
     * 图片预览框 构造方法
     *
     */
    public ImgBlock(){
        //父属性及子属性设定
        this.setPadding(new Insets(10));
        this.setTop(topBar);
        this.setBottom(butBar);
        this.getStyleClass().add("block-bg");

        // 边框阴影设置
        DropShadow dropShadow =new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        dropShadow.setSpread(0.0);
        this.setEffect(dropShadow);


        //私有属性设定
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
        downLoad.getItems().addAll(save,saveAs);
        downLoad.setGraphic(ivDwonLoad);
        downLoad.getStyleClass().addAll("block-menu-bt");


        //底部转换菜单设定
        MenuButton trans = new MenuButton();
        trans.setPadding(new Insets(0,0,0,10));
        MenuItem toJpg = new MenuItem("To JPG");
        MenuItem toPng = new MenuItem("To PNG");
        save.getStyleClass().addAll("block-menu-basic");
        saveAs.getStyleClass().addAll("block-menu-basic");
        trans.getItems().addAll(toJpg,toPng);
        trans.getStyleClass().addAll("block-menu-bt");
        // 状态及关闭图标设定
        butBar.getChildren().add(trans);


    }
    public ImgBlock(String imgUrl){
        ivimg = new ImageView(new Image(imgUrl));
    }
}
