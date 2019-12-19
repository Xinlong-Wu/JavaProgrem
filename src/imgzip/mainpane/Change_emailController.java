package imgzip.mainpane;

import imgzip.LoginSignIn.DataBaseController;
import imgzip.LoginSignIn.GlobalStringManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 @Author:  吴泳仪
 @Date: 2019.12.17
 */
public class Change_emailController implements Initializable {
    public String a;
    @FXML
    private Button certain;
    @FXML
    private TextField text;

    public void initialize(URL lacation, ResourceBundle resources){

        /**
         在Change_email中按确定按钮后把云端
         数据库中对应的账号的邮箱换成TextField中的邮箱
         并更新全局变量GlobalStringManager中的email
         结束后更新并打开personal
         */
        certain.setOnAction(e -> {
            a = text.getText();
            DataBaseController loginInstruction = new DataBaseController();
            ResultSet rs = null;
            String currentInstruction = "UPDATE login SET email=" + "'" + a + "'" + "WHERE username=" + "'" + GlobalStringManager.getAccount() + "'";
            loginInstruction.queryUpdate(currentInstruction);
            loginInstruction.close();
            GlobalStringManager.setEmial(a);
            new Personal();
            Stage stage = (Stage) certain.getScene().getWindow();
            stage.close();
        });

    }
}
