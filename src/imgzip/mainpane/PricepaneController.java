package imgzip.mainpane;

import imgzip.FunctionPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 @Author:吴泳仪
 @Date:2019.12.09
 */
public class PricepaneController implements Initializable {
    @FXML
    private Button personal;
    @FXML
    private Button functionpage;
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private Button button1;
    @FXML
    private Button button3;

    public void initialize(URL lacation, ResourceBundle resources){
        /**
         本页面连接到functionpage
         */
        functionpage.setOnAction(e -> {
            new FunctionPane();
            Stage stage = (Stage)functionpage.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到course
         */
        course.setOnAction(e -> {
            new Course();
            Stage stage = (Stage)course.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到price
         */
        price.setOnAction(e -> {
            new Pricepane();
            Stage stage = (Stage)price.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到homepage，登出
         */
        signout.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)signout.getScene().getWindow();
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
         本页面连接到priceimg（微信收费二维码页面）
         */
        button1.setOnAction(e -> {
            new Priceimg();
        });

        /**
         本页面连接到priceimg（微信收费二维码页面）
         */
        button3.setOnAction(e -> {
            new Priceimg();
        });

    }
}
