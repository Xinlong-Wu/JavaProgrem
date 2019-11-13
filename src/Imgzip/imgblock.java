package Imgzip;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    static String WAITING = "res/icon/waiting.png";
    static String DONE = "res/icon/done.png";
    static String DOWNLOAD = "res/icon/download.png";

    private ImageView ivimg = null;
    private ImageView ivstate = new ImageView(new Image(WAITING));
    private ImageView ivDwonLoad = new ImageView(new Image(DOWNLOAD));

    HBox topBar = new HBox();

//    Menu
    public ImgBlock(){
        //父属性及子属性设定
        this.setPadding(new Insets(10));
        super.setTop(topBar);
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
        ivstate.setFitHeight(20);
        ivstate.setFitWidth(20);
        ivDwonLoad.setFitHeight(20);
        ivDwonLoad.setFitWidth(20);

        // 顶部栏属性设定
        topBar.getStyleClass().setAll("block-topbar");
        topBar.setPadding(new Insets(0,0,0,10));

        // 状态图标设定
        topBar.getChildren().add(ivstate);


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
        topBar.getChildren().add(downLoad);

    }
    public ImgBlock(String imgUrl){
        ivimg = new ImageView(new Image(imgUrl));
    }
}
