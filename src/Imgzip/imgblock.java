package Imgzip;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;


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

    GridPane topBar = new GridPane();

//    Menu
    public ImgBlock(){
        //父属性设定
        super.setHeight(380);
        super.setWidth(300);
        super.setTop(topBar);
        this.getStyleClass().add("block-bg");

        //私有属性设定
        ivstate.setFitHeight(20);
        ivstate.setFitWidth(20);
        ivDwonLoad.setFitHeight(20);
        ivDwonLoad.setFitWidth(20);

        // 顶部栏属性设定
        topBar.setGridLinesVisible(true);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setHgap(6);
        topBar.setVgap(6);
        topBar.setPadding(new Insets(5,10,5,10));
        topBar.setPrefSize(370,20);

        // 状态图标设定
        topBar.add(ivstate,0,0);

        //保存菜单设定
        MenuButton downLoad = new MenuButton();
        MenuItem save = new MenuItem("保存");
        MenuItem saveAs = new MenuItem("另存为");
        save.getStyleClass().addAll("menu-basic");
        saveAs.getStyleClass().addAll("menu-basic");
        downLoad.getItems().add(save);
        downLoad.getItems().add(saveAs);
        downLoad.setGraphic(ivDwonLoad);
        downLoad.getStyleClass().addAll("block-bg");
        topBar.add(downLoad,1,0);

    }
    public ImgBlock(String imgUrl){
        ivimg = new ImageView(new Image(imgUrl));
    }
}
