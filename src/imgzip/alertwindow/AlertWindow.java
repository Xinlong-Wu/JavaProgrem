package imgzip.alertwindow;

import imgzip.interfaces.ButtonHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private String alertTitle = "关于imgZip";
    private Button ok = new Button("好的");
    private Button View = new Button("访问主页");
    private VBox pane = new VBox(5);
    private VBox btBox = new VBox(10);
    private int time = -1;

    /**
     * 跳转项目网站模式
     */
    public AlertWindow(){
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
        lbinfo.getStyleClass().addAll("disc");

        ImageView tar = new ImageView(new Image("res/icon/target.png"));
        tar.setFitHeight(20);
        tar.setFitWidth(20);
        ok.setGraphic(tar);
        ok.setGraphicTextGap(10);
        ok.setAlignment(Pos.CENTER_LEFT);
        ok.getStyleClass().addAll("button");
        ImageView tar2 = new ImageView(new Image("res/icon/target.png"));
        tar2.setFitHeight(20);
        tar2.setFitWidth(20);
        View.setGraphic(tar2);
        View.setGraphicTextGap(10);
        View.setAlignment(Pos.CENTER_LEFT);
        View.getStyleClass().addAll("button");


        btBox.getChildren().addAll(ok,View);
        btBox.getStyleClass().addAll("ppane","btBox");

        pane.getStyleClass().addAll("ppane");
        pane.getChildren().addAll(tittle,lbinfo,btBox);
    }

    /**
     * 自定义警告文本模式
     */
    public AlertWindow(String alertTitle, String desc){
        this.alertTitle = alertTitle;

        HBox tittle = new HBox(10);
        ImageView iminfo = new ImageView(new Image("res/icon/Information.png"));
        iminfo.getStyleClass().addAll("iminfo");
        iminfo.setFitHeight(45);
        iminfo.setFitWidth(45);
        Label lbinfocT = new Label(alertTitle);
        lbinfocT.getStyleClass().addAll("lbinfocT");
        tittle.getChildren().addAll(iminfo,lbinfocT);
        tittle.setPadding(new Insets(0,0,0,15));
        tittle.getStyleClass().addAll("tittle");

        Label lbinfo = new Label(desc);

        ImageView tar = new ImageView(new Image("res/icon/target.png"));
        tar.setFitHeight(20);
        tar.setFitWidth(20);
        ok.setGraphic(tar);
        ok.setGraphicTextGap(10);
        ok.setAlignment(Pos.CENTER_LEFT);
        ok.getStyleClass().addAll("button");

        btBox.getChildren().addAll(ok);
        btBox.getStyleClass().addAll("ppane","btBox");

        pane.getStyleClass().addAll("ppane");
        pane.getChildren().addAll(tittle,lbinfo,btBox);
    }

    /**
     * 定时自动关闭模式
     */
    public AlertWindow(String alertTitle, String desc,int delay){
        this.time = delay;
        this.alertTitle = alertTitle;

        HBox tittle = new HBox(10);
        ImageView iminfo = new ImageView(new Image("res/icon/Information.png"));
        iminfo.getStyleClass().addAll("iminfo");
        iminfo.setFitHeight(45);
        iminfo.setFitWidth(45);
        Label lbinfocT = new Label(alertTitle);
        lbinfocT.getStyleClass().addAll("lbinfocT");
        tittle.getChildren().addAll(iminfo,lbinfocT);
        tittle.setPadding(new Insets(0,0,0,15));
        tittle.getStyleClass().addAll("tittle");

        Label lbinfo = new Label(desc);

        ImageView tar = new ImageView(new Image("res/icon/target.png"));
        tar.setFitHeight(20);
        tar.setFitWidth(20);
        ok.setGraphic(tar);
        ok.setGraphicTextGap(10);
        ok.setAlignment(Pos.CENTER_LEFT);
        ok.getStyleClass().addAll("button");

        btBox.getChildren().addAll(ok);
        btBox.getStyleClass().addAll("ppane","btBox");

        pane.getStyleClass().addAll("ppane");
        pane.getChildren().addAll(tittle,lbinfo,btBox);
    }

    /**
     * 自定义第二个按钮行为，lambda表达式作为参数
     * @param value
     */
    public final void anotherButton(ButtonHandler<VBox> value) {
        VBox vBox = new VBox();
        vBox.getStyleClass().addAll("ppane");
        value.start(vBox);
    }

    public ObservableList<Node> getBtBoxChildren() {
        return btBox.getChildren();
    }

    /**
     * 警告窗口启动器
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {


        Scene scene = new Scene(pane);
        scene.getStylesheets().add("css/alert.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle(alertTitle);
        primaryStage.getIcons().add(new Image("res/icon/Information.png"));
        primaryStage.setResizable(false);
        primaryStage.show();

        if(time != -1){
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(time);
                    Platform.runLater(() -> primaryStage.close());
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        ok.setOnAction(event -> {
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
