package imgzip.Login_SignIn;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 @Author:   肖尧
 @Date: 2019.11.24
 */

public class CreateSuccessfullyController{
    @FXML
    private Button signUp;

    @FXML
    private Button signIn;


    /**
     * 点击后sign up 后回到登录页面。
     */
    public void backToLogin() {
        Stage stage = (Stage) signUp.getScene().getWindow();
        new LoginBeginner();
        stage.close();

    }


    /**
     * 点击后sign in again 后继续注册。
     */
    public void backToSignIn() {
        Stage stage = (Stage) signIn.getScene().getWindow();
        new CreateAccount();
        stage.close();

    }
}
