package imgzip.mainwindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * AlertWindow class
 *
 * @author 乌鑫龙
 * @date 2019/12/3
 */
public class AlertWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox tittle = new HBox(10);
        ImageView iminfo = new ImageView(new Image("res/icon/Information.png"));
        iminfo.getStyleClass().addAll("iminfo");
        iminfo.setFitHeight(45);
        iminfo.setFitWidth(45);
        Label lbinfocT = new Label("ImgZip V 0.1.0");
        lbinfocT.getStyleClass().addAll("lbinfocT");
        tittle.getChildren().addAll(iminfo,lbinfocT);
        tittle.setPadding(new Insets(0,0,0,15));
        tittle.getStyleClass().addAll("tittle");

        Label lbinfo = new Label("Created by wxl \n https://gitee.com/fenglingerr/JavaProgrem");


        Button oK = new Button("好的");
        ImageView tar = new ImageView(new Image("res/icon/target.png"));
        tar.setFitHeight(20);
        tar.setFitWidth(20);
        oK.setGraphic(tar);
        oK.setGraphicTextGap(10);
        oK.setAlignment(Pos.CENTER_LEFT);
        oK.getStyleClass().addAll("button");
        Button View = new Button("访问主页");
        ImageView tar2 = new ImageView(new Image("res/icon/target.png"));
        tar2.setFitHeight(20);
        tar2.setFitWidth(20);
        View.setGraphic(tar2);
        View.setGraphicTextGap(10);
        View.setAlignment(Pos.CENTER_LEFT);
        View.getStyleClass().addAll("button");

        VBox btBox = new VBox(5);
        btBox.getChildren().addAll(oK,View);
        btBox.getStyleClass().addAll("ppane","btBox");

        VBox pane = new VBox(5);
        pane.getStyleClass().addAll("ppane");
        pane.getChildren().addAll(tittle,lbinfo,btBox);

        Scene scene = new Scene(pane,400,250);
        scene.getStylesheets().add("css/alert.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("关于imgZip");
        primaryStage.getIcons().add(new Image("res/icon/Information.png"));
        primaryStage.setResizable(false);
        primaryStage.show();

        oK.setOnAction(event -> {
            primaryStage.close();
        });

        View.setOnAction(event -> {
            if(Desktop.isDesktopSupported()){
                URI uri = URI.create("https://gitee.com/fenglingerr/JavaProgrem");
                Desktop dp = Desktop.getDesktop();
                if(dp.isSupported(Desktop.Action.BROWSE)){
                    try {
                        dp.browse(uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
