package imgzip.LoginSignIn;

import imgzip.mainpane.Personal;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;

import java.sql.SQLException;


/**
    @Author:肖尧
    @Date:2019.11.24
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

    @FXML
    private Label passwordWrong;


    /**
    检查用户是否输入了用户名和密码，
    如果只输入了用户名，或只输入了密码，或两者都没输入，则按钮LOGIN无法被点击，
    当用户名与密码同时被输入时，按钮LOGIN才能被点击。
     */
    public void check(){

        String stringAccount = account.getText();
        String stringPassword = password.getText();
        String ifEmpty = "";

        if ( (stringAccount.equals(ifEmpty) || stringPassword.equals(ifEmpty))){
            login.setDisable(true);

        }else if ((!stringAccount.equals(ifEmpty) && !stringPassword.equals(ifEmpty))){
            login.setDisable(false);
        }

        if(passwordWrong.isVisible()){
            passwordWrong.setVisible(false);
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
     ②：如果账号存在且密码正确，则进入主页面（尚未完成）。
     ③：如果勾选了记住账号和密码，则在下一次打开页面时会记住上一次的密码。
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

                    remember();
                    //登入主页面，待定
                    if ((passwordWrong.isVisible())){
                        passwordWrong.setVisible(false);
                    }
                    GlobalStringManager.setAccount(account.getText());

                    Stage stage = (Stage)account.getScene().getWindow();
                    new Personal();
                    stage.close();

                }else{
                    passwordWrong.setVisible(true);
                }

            }else {
                passwordWrong.setVisible(true);
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            loginInstruction.close();
        }
    }


    /**
    设置cancel按钮，可以直接点击这里完成窗口的关闭。
 */
    public void cancel(){

        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }



    /**
     设置记住账号密码按钮的方法。
     */

    public void remember(){
        boolean ifclick = remember.isSelected();
        String judgeIfclick = "";

        if(ifclick){
            judgeIfclick = "true";
        }else {
            judgeIfclick = "false";
        }

        try {
            FileWriter fw = new FileWriter("txtFile/RememberAccount&Password.txt");
            BufferedWriter fis = new BufferedWriter(fw);

            fis.write(account.getText() +"|"+ password.getText() + "|" + judgeIfclick );
            fis.newLine();

            fis.close();
            fw.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     点击注册页面的方法，用户点击以后直接进入注册页面。
     */
    public void createAccount(){
        new CreateAccount();
        Stage stage = (Stage)createAccount.getScene().getWindow();
        stage.close();
    }
    /**
     点击忘记密码页面的方法，用户点击以后直接进入找回密码页面。
     */
    public void findPassword(){
        new FindPassword();
        Stage stage = (Stage)cantSign.getScene().getWindow();
        stage.close();
    }

}

