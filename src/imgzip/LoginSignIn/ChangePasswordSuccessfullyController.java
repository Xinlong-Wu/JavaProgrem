package imgzip.LoginSignIn;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        new LoginBeginner();
        stage.close();
    }

}
