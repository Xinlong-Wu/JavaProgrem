package Imgzip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


/**
 * imgblock class
 *
 * @author 乌鑫龙
 * @date 2019/11/12
 */
class ImgBlock extends BorderPane {
    static String WAITING = "icon/waiting.png";
    static String DONE = "icon/done.png";
    static String DOWNLOAD = "icon/download.png";

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
        this.setStyle("-fx-background-color: #ffffff;");

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
        MenuBar downLoadBar = new MenuBar();
        downLoadBar.getStyleClass().add("downLoadBar");
        Menu downLoad = new Menu();
//        downLoad.getStyleClass().addAll()
        downLoad.setGraphic(ivDwonLoad);
        downLoadBar.getMenus().add(downLoad);

        topBar.add(downLoadBar,1,0);

    }
    public ImgBlock(String imgUrl){
        ivimg = new ImageView(new Image(imgUrl));
    }


}
