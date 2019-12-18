package imgzip.mainpane;

import imgzip.LoginSignIn.LoginBeginner;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Button remember;
    @FXML
    private Button change;
    @FXML
    private Button pic_upload;
    @FXML
    private Button pics_upload;
    @FXML
    private ImageView gif;


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
         把改变图像格式教程的gif放到page版面上
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
