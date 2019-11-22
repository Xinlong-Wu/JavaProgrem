package imgzip;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
    @Author:肖尧
    @Date:2019.11.22
 */
public class LoginController {


    @FXML
    private TextField account;

    @FXML
    private TextField password;

    @FXML
    private CheckBox remember;

    @FXML
    private Button login;

    @FXML
    private Button cancel;

    @FXML
    private Button cantSign;

    @FXML
    private Button createAccount;



    /**
    检查用户是否输入了用户名和密码，
    如果只输入了用户名，或只输入了密码，或两者都没输入，则按钮LOGIN无法被点击，
    当用户名与密码同时被输入时，按钮LOGIN才能被点击。
     */
    public void check(){

        String stringAccount = account.getText();
        String stringPassword = password.getText();
        String ifEmpty = "";

        if ( (stringAccount.equals(ifEmpty) || stringPassword.equals(ifEmpty)) && login.isDisable() == false){
            login.setDisable(true);

        }else if ((!stringAccount.equals(ifEmpty) && !stringPassword.equals(ifEmpty))){
            login.setDisable(false);
        }
    }


    /**
    检查是否已经连上数据库
     */

    public void dataBaseConnectionCheck(){
        DataBaseController loginInstruction = new DataBaseController();
        try {
            loginInstruction.jdbcConnection();
        }catch (Exception ex){
            ex.printStackTrace();
            loginInstruction.close();
        }
    }

    /**
    用户点击此按钮时，
    ①：如果账号不存在或账号存在但是密码错误，则进入密码错误页面。
    ②：如果账号存在且密码正确，则进入主页面（尚未完成）
     */

    public void login(){

        DataBaseController loginInstruction = new DataBaseController();
        ResultSet rs = null;

        try{

            String currentInstruction = "SELECT pwd FROM login WHERE userName=" + "'" + account.getText().trim()+ "'";
            rs = loginInstruction.queryExcecute(currentInstruction);


            if (rs.next()) {
                String rightPassword = rs.getString(1);
                if (rightPassword.equals(password.getText())){
                    //登入主页面，待定
                    System.out.println("登录成功");

                }else{
                    new WrongPassword();

                }

            }else {
                new WrongPassword();

            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            loginInstruction.close();
        }

    }


    /**
    设置back事件，
    当用户点击该按钮的时候，跳转至登录界面
 */
    public void cancel(){

        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

}
