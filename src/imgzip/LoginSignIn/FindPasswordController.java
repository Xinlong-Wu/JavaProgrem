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
     * ①：如果验证的账号为空则无法点击验证按钮
     * ②：当输入的账号符合规则时，依旧会检查邮箱符不符合规则
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
     * ①如果输入的邮箱为空，则按钮无法被点击。
     * ②当输入的邮箱符合规则时，依旧会检查账号符不符合规则。
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
     * ①：若账号与邮箱不匹配，或账号在数据库中不存在，则会弹出窗口提醒用户账号出错
     * ②：若邮箱与账号不匹配，或邮箱在数据库中不存在，则会弹出窗口提醒用户邮箱出错
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


                /**
                 * 当用户认证失败时，告诉用户是账户名错误还是邮箱错误。
                 */
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

    /**
     * ①当用户点击“Signin”超链接时，会自动跳转至登录页面。
     */
    public void backToSignIn(){
        Stage stage = (Stage) verify.getScene().getWindow();
        new LoginBeginner();
        stage.close();
    }
}




