package imgzip.LoginSignIn;

import imgzip.alertwindow.AlertWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class FindPasswordController {

    @FXML
    private TextField account;

    @FXML
    private TextField email;

    @FXML
    private Button verify;


    /**
     * 检查账号是否为空，设置按钮是否可被点击。
     */
    boolean accountIsEmpty = true;
    public void checkAccountIsEmpty(){

        if("".equals(account.getText())){
            accountIsEmpty = false;

            if(!verify.isDisable()){
                verify.setDisable(true);
            }

        }else {
            accountIsEmpty = false;
            if(!emailIsEmpty && verify.isDisable()){
                verify.setDisable(false);
            }
        }
    }

    /**
     * 检查邮箱是否为空，设置按钮是否可被点击。
     */
    boolean emailIsEmpty = true;
    public void checkEmailIsEmpty(){

        if("".equals(email.getText())){
            emailIsEmpty = true;

            if(!verify.isDisable()){
                verify.setDisable(true);
            }

        }else {
            emailIsEmpty = false;
            if(!accountIsEmpty && verify.isDisable()){
                verify.setDisable(false);
            }
        }
    }



    /**
     * 检查用户名和邮箱是否唯一匹配，如果不匹配则会弹出提示窗口，如果匹配则可以进入修改密码的页面。
     */
    public void verifyEmailAndAccount(){

    DataBaseController verifyInstruction = new DataBaseController();
    ResultSet rs = null;

    try{

        String currentInstruction = "SELECT email FROM login WHERE userName=" + "'" + account.getText().trim() + "'";
        rs = verifyInstruction.queryExcecute(currentInstruction);

        if(rs.next()){

            if(rs.getString(1).equals(email.getText())){

                GlobalStringManager.setAccount(account.getText());
                new ChangePassword(0);
                Stage stage = (Stage)account.getScene().getWindow();
                stage.close();

            }else {
                AlertWindow alertWindow = new AlertWindow("验证失败","YOUR EMAIL IS WRONG");  // 此处写警告弹窗的标题和内容
                // 用于新加按钮，可以新加多个，最后添加在VBox中
                alertWindow.start(new Stage());
            }

        }else {
            AlertWindow alertWindow = new AlertWindow("验证失败","We are sorry to inform you that:\nThis Account doesn't exist!");  // 此处写警告弹窗的标题和内容
            // 用于新加按钮，可以新加多个，最后添加在VBox中
            alertWindow.start(new Stage());
        }


    }catch (Exception e){


    }finally {
        verifyInstruction.close();
    }

    }


    public void backToSignIn(){
        Stage stage = (Stage) verify.getScene().getWindow();
        new LoginBeginner();
        stage.close();
    }
}




