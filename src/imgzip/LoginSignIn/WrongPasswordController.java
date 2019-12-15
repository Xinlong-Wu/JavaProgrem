package imgzip.LoginSignIn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 @Author:肖尧
 @Date:2019.11.24
 */
public class WrongPasswordController {

    @FXML
    private Button createAccount;

    @FXML
    private Button findPassword;

    @FXML
    private Button back;


    /**
    设置createAccount事件，
    当用户点击该按钮的时候，跳转至用户注册页面
     */

    public void createAccount(){
        new CreateAccount();
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }


    /**
    设置findPassword事件，
    当用户点击该按钮的时候，跳转至用户找回密码页面
     */
    public void findPassword(){

    }


    /**
    设置back事件，
    当用户点击该按钮的时候，跳转至登录界面
     */
    public void back(){

        Stage stage = (Stage)back.getScene().getWindow();
        new Loginbeginner();
        stage.close();

    }


}
