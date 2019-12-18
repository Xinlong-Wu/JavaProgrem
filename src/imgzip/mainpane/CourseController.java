package imgzip.mainpane;

import com.sun.org.apache.xalan.internal.xsltc.dom.CurrentNodeListFilter;
import imgzip.FunctionPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ResourceBundle;

/**
 @Author:  吴泳仪
 @Date: 2019.12.10
 */
public class CourseController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private Button functionpage;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Button forget;
    @FXML
    private Button remember;
    @FXML
    private Button change;
    @FXML
    private Button pic_upload;
    @FXML
    private Button pics_upload;
    @FXML
    private Button personal;
    @FXML
    private ImageView gif;



    public void initialize(URL lacation, ResourceBundle resources){
        /**
        本页面连接到homepage
         */
        signout.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)signout.getScene().getWindow();
            stage.close();
        });

        /**
         course页面自我更新到初始状态
         */
        course.setOnAction(e -> {
            new Course();
            Stage stage = (Stage)course.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到price
         */
        price.setOnAction(e->{
            new Pricepane();
            Stage stage = (Stage)price.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到functionpage
         */
        functionpage.setOnAction(e -> {
            new FunctionPane();
            Stage stage = (Stage)functionpage.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到personal
         */
        personal.setOnAction(e -> {
            new Personal();
            Stage stage = (Stage)personal.getScene().getWindow();
            stage.close();
        });




        /**
         把登录教程的gif放到page版面上
         */
        login.setOnAction(e -> {
            Image image = new Image("/res/icon/Login.gif");
            gif.setImage(image);
        });

        /**
         把注册教程的gif放到page版面上
         */
        register.setOnAction(e -> {
            Image image = new Image("/res/icon/createAccount.gif");
            gif.setImage(image);
        });

        /**
         把忘记密码教程的gif放到page版面上
         */
        forget.setOnAction(e->{
            Image image = new Image("/res/icon/ChangePassword.gif");
            gif.setImage(image);
        });

        /**
         把记住用户名教程的gif放到page版面上
         */
        remember.setOnAction(e->{
            Image image = new Image("/res/icon/RememberAccount.gif");
            gif.setImage(image);
        });

        /**
         把图片格式转换教程的gif放到page版面上
         */
        change.setOnAction( e-> {
            Image image = new Image("/res/icon/格式转换.gif");
            gif.setImage(image);
        });

        /**
         把单一图片上传教程的gif放到page版面上
         */
        pic_upload.setOnAction( e-> {
            Image image = new Image("/res/icon/单一图片上传.gif");
            gif.setImage(image);
        });

        /**
         把批量图片上传教程的gif放到page版面上
         */
        pics_upload.setOnAction( e-> {
            Image image = new Image("/res/icon/批量图片上传.gif");
            gif.setImage(image);
        });
    }

}

