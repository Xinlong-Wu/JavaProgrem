package imgzip.Login_SignIn;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.misc.Resource;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 @Author:   肖尧
 @Date: 2019.12.12
 */
public class ChangePasswordSuccessfullyController {

    @FXML
    Button SignUp = new Button();


    /**
     * 返回登录页面
     */
    public void backToLogin(){
        Stage stage = (Stage) SignUp.getScene().getWindow();
        new Loginbeginner();
        stage.close();
    }

}
