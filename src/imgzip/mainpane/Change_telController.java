package imgzip.mainpane;

import imgzip.LoginSignIn.DataBaseController;
import imgzip.LoginSignIn.GlobalStringManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Change_telController implements Initializable {
    public String b;
    @FXML
    private Button certain;
    @FXML
    private TextField text;

    public void initialize(URL lacation, ResourceBundle resources){

        /**
         在Change_tel中按确定按钮后把云端
         数据库中对应的账号的电话号码换成TextField中的电话号码
         并更新全局变量GlobalStringManager中的tel
         结束后更新并打开personal页面
         */
        certain.setOnAction(e -> {
            b = text.getText();
            DataBaseController loginInstruction = new DataBaseController();
            ResultSet rs = null;
            String currentInstruction = "UPDATE login SET tel=" + "'" + b + "'" + "WHERE username=" + "'" + GlobalStringManager.getAccount() + "'";
            loginInstruction.queryUpdate(currentInstruction);
            loginInstruction.close();
            GlobalStringManager.setEmial(b);
            new Personal();
            Stage stage = (Stage) certain.getScene().getWindow();
            stage.close();
        });

    }
}
