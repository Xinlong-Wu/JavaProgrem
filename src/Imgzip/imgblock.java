package Imgzip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    static String WAITING = "file:res/icon/waiting.png";
    static String DONE = "file:res/icon/done.png";
    ImageView img = null;
    ImageView state = new ImageView(new Image(WAITING));
    GridPane topBar = new GridPane();

//    Menu
    public ImgBlock(){
        super.setHeight(380);
        super.setWidth(300);
        super.setTop(topBar);

        topBar.setGridLinesVisible(true);
        topBar.setAlignment(Pos.CENTER);
        topBar.setHgap(6);
        topBar.setVgap(6);

        state.setFitHeight(20);
        state.setFitWidth(20);
        topBar.add(state,0,0);

    }
    public ImgBlock(String imgUrl){
        img = new ImageView(new Image(imgUrl));
    }


}
