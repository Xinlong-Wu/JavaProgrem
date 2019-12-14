package imgzip.mainpane;

import com.sun.org.apache.xalan.internal.xsltc.dom.CurrentNodeListFilter;
import imgzip.Login_SignIn.LoginBeginner;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 @Author:  吴泳仪
 @Date: 2019.12.13
 */
public class CourseControllernone implements Initializable {
    @FXML
    private Button coursenone;
    @FXML
    private Button pricenone;
    @FXML
    private Button loginnone;
    @FXML
    private Button homepagenone;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Button forget;
    @FXML
    private Button change;
    @FXML
    private Button compress;
    @FXML
    private BorderPane page;


    public void initialize(URL lacation, ResourceBundle resources){
        /**
         本页面连接到course未登录页面
         */
        coursenone.setOnAction(e -> {
            new Course(1);
            Stage stage = (Stage)coursenone.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到price未登录状态的页面
         */
        pricenone.setOnAction(e -> {
            new Pricepane(1);
            Stage stage = (Stage)pricenone.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到登录页面
         */
        loginnone.setOnAction(e -> {
            Stage stage = (Stage)loginnone.getScene().getWindow();
            new LoginBeginner();
            stage.close();
        });

        /**
         本页面连接到homepage页面
         */
        homepagenone.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)homepagenone.getScene().getWindow();
            stage.close();
        });

        /**
         把登录教程的gif放到page版面上
         */
        login.setOnAction(e -> {
//            page.getChildren().clear();
//            Image image = new Image("pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });

        /**
         把注册教程的gif放到page版面上
         */
        register.setOnAction(e -> {
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });

        /**
         把忘记密码教程的gif放到page版面上
         */
        forget.setOnAction(e->{
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });

        /**
         把改变图像格式教程的gif放到page版面上
         */
        change.setOnAction( e-> {
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });

        /**
         把图片压缩教程的gif放到page版面上
         */
        compress.setOnAction( e-> {
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });
    }
}
