package imgzip.mainpane;

import imgzip.FunctionPane;
import imgzip.LoginSignIn.ChangePassword;
import imgzip.LoginSignIn.DataBaseController;
import imgzip.LoginSignIn.GlobalStringManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 @Author:  吴泳仪
 @Date: 2019.12.14
 */
public class PersonalController implements Initializable {
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private Button functionpage;
    @FXML
    private Button changeemail;
    @FXML
    private Button changetel;
    @FXML
    private Button changepw;
//    @FXML
//    public static Pane accountp;
//    @FXML
//    private Pane emailp;

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
         修改邮箱
         */
        changeemail.setOnAction(e -> {
            new Change_email();
            Stage stage = (Stage) changeemail.getScene().getWindow();
            stage.close();
        });

        /**
         修改电话号
         */
        changetel.setOnAction(e -> {
            new Change_tel();
            Stage stage = (Stage) changetel.getScene().getWindow();
            stage.close();
        });

        /**
         修改密码
         */
        changepw.setOnAction(e -> {
            new ChangePassword();
            Stage stage = (Stage)changepw.getScene().getWindow();
            stage.close();
        });
    }
}
