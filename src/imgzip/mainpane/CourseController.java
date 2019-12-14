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
 @Date: 2019.12.10
 */
public class CourseController implements Initializable {
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
    private Button change;
    @FXML
    private Button compress;
    @FXML
    private BorderPane page;



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
         把登录教程的gif放到page版面上
         */
        login.setOnAction(e -> {
            page.getChildren().clear();
            Image image = new Image("file:res/icon/pkq.gif");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            page.setCenter(imageView);
            new Course();
            Stage stage = (Stage)course.getScene().getWindow();
            stage.close();
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
         把图片格式转换教程的gif放到page版面上
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
