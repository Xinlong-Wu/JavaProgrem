package imgzip;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
    @Author:肖尧
    @Date:2019.11.20
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



    /*
    检查用户是否输入了用户名和密码，
    如果只输入了用户名，或只输入了密码，或两者都没输入，则按钮LOGIN无法被点击，
    当用户名与密码同时被输入时，按钮LOGIN才能被点击。
     */
    public void check(){

        String stringAccount = account.getText();
        String stringPassword = password.getText();
        String ifEmpty = "";

        if (stringAccount.equals(ifEmpty) || stringPassword.equals(ifEmpty)){
            login.setDisable(true);

        }else {
            login.setDisable(false);

        }

    }


    public void dataBaseConnectionCheck(){
        DataBaseController loginInstruction = new DataBaseController();
        try {
            loginInstruction.jdbcConnection();
        }catch (Exception ex){
            ex.printStackTrace();
            loginInstruction.close();
        }
    }



    public void login(){

        DataBaseController loginInstruction = new DataBaseController();

        try{

            String currentInstruction = "SELECT pwd FROM login WHERE userName=" + "'" + account.getText().trim()+ "'";
            System.out.println("here1");
            ResultSet rs = loginInstruction.queryExcecute(currentInstruction);
            System.out.println("hereax");


            if (rs.next()) {
                System.out.println("find");
            }else {
                System.out.println("not find");
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            loginInstruction.close();
        }

    }

}
