package imgzip.LoginSignIn;

import imgzip.LoginSignIn.LoginBeginner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;

/**
 @Author:   肖尧
 @Date: 2019.12.12
 */
public class CreateAccountController {

    @FXML
    private TextField userName;

    @FXML
    private TextField passWord;

    @FXML
    private TextField email;

    @FXML
    private TextField telephone;

    @FXML
    private CheckBox agree;

    @FXML
    private Button createAccount;

    @FXML
    private Hyperlink privacy;

    @FXML
    private Label already;

    @FXML
    private Label already2;

    @FXML
    private Hyperlink signIn;


    /**
     * SignIn点击后,返回登录页面并关闭注册页面
     */
    public void backToLogin() {
        Stage stage = (Stage) signIn.getScene().getWindow();
        new LoginBeginner();
        stage.close();

    }

    /**
     * 检查账号是否存在，如果存在则无法点击注册按钮，且会提醒用户。
     */
    boolean judgeAccountExists = false;
    public void checkAccountExistence() {
        DataBaseController createAccountInstruction = new DataBaseController();
        ResultSet rs = null;

        try {
            /**
             第一步，检查数据库中是否有该账号存在
             */
            String currentInstruction = "SELECT pwd FROM login WHERE userName=" + "'" + userName.getText().trim() + "'";
            rs = createAccountInstruction.queryExcecute(currentInstruction);

            /**
             * 第二步：
             * ①如果账号为空，则无法点击注册按钮。
             * ②如果账号存在，无法点击。
             * ③如果账号不存在，邮箱存在，无法点击。
             * ④如果账号邮箱都不存在，但是未同意服务条款，无法点击。
             * ⑤如果账号邮箱都不存在，且同意了服务条款，才可被点击。
             */

            if("".equals(userName.getText())){
                createAccount.setDisable(true);

            }else {
                if (rs.next()) {
                    judgeAccountExists = true;
                    already.setVisible(true);

                    if(!createAccount.isDisable()){
                        createAccount.setDisable(true);

                    }
                }else{
                    judgeAccountExists = false;
                    already.setVisible(false);

                    if(!judgeEmialexist && agree.isSelected() && !"".equals(passWord.getText())){
                        createAccount.setDisable(false);

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            createAccountInstruction.close();
        }

    }

    /**
     * 检查邮箱是否存在，如果存在则无法点击注册按钮，且会提醒用户。
     */

    boolean judgeEmialexist = false;
    public void checkEmailExistence() {

        DataBaseController createAccountInstruction = new DataBaseController();
        ResultSet rs2 = null;

        try {
            String currentInstruction2 = "SELECT pwd FROM login WHERE email=" + "'" + email.getText().trim() + "'";
            rs2 = createAccountInstruction.queryExcecute(currentInstruction2);

            /**
             * 第二步：
             * ①如果账号为空，则无法点击注册按钮。
             * ②如果账号存在，无法点击。
             * ③如果账号不存在，邮箱存在，无法点击。
             * ④如果账号邮箱都不存在，但是未同意服务条款，无法点击。
             * ⑤如果账号邮箱都不存在，且同意了服务条款，才可被点击。
             */
            if ("".equals(email.getText())){
                createAccount.setDisable(true);

            }else {
                if (rs2.next()) {
                    judgeEmialexist = true;
                    already2.setVisible(true);

                    if(!createAccount.isDisable()){
                        createAccount.setDisable(true);

                    }
                }else{
                    judgeEmialexist = false;
                    already2.setVisible(false);

                    if(!judgeAccountExists && agree.isSelected() && !"".equals(passWord.getText())){
                        createAccount.setDisable(false);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            createAccountInstruction.close();

        }
    }

    /**
     * CreateAccount点击后，将账号插入数据库中
     */
    public void createAccount() {
        DataBaseController createAccountInstruction = new DataBaseController();
        ResultSet rs = null;

        try {
            /**
             向数据库中插入数据
             */
            String encryptedPassword = HashUtil.hash(passWord.getText());

            String currentInstruction = "INSERT INTO login (userName,pwd,email,tel) values(" + "'"+userName.getText().trim()+ "'" + "," + "'"+ encryptedPassword.trim()+ "'" + "," + "'"+ email.getText().trim() + "'"+ "," +  "'"+telephone.getText().trim()+ "'"+")" ;
            createAccountInstruction.queryUpdate(currentInstruction);

            /**
             检查数据库中是否成功插入数据
             */
            String currentInstruction2 = "SELECT pwd FROM login WHERE userName=" + "'" + userName.getText().trim() + "'";
            rs = createAccountInstruction.queryExcecute(currentInstruction2);

            if(rs.next()){
                Stage stage = (Stage) signIn.getScene().getWindow();
                new CreateSuccessfully();
                stage.close();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SORRY!");
                alert.setHeaderText("Something's wrong!");
                alert.setContentText("The Account is not created");
                alert.showAndWait();

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("456");

        } finally {
            createAccountInstruction.close();

        }
    }

    /**
     * 检查是否点击了同意privacy协议，如果不同意，则无法点击 createaccount 按钮。
     */
    public void checkIfSelectedPrivacy(){


        boolean userNmae = "".equals(userName.getText());
        boolean emial = "".equals(email.getText());

        if(agree.isSelected() && !userNmae && !emial && createAccount.isDisable() && !already.isVisible() && !already2.isVisible() && !"".equals(passWord.getText())){
            createAccount.setDisable(false);
        }else {
            createAccount.setDisable(true);
        }
    }

    /**
     * 点击Privacy 的 hyperlink后，跳出网页进入隐私条例说明。
     */
    public void openPrivacy(){

        try{
            try {
                Desktop.getDesktop().browse(new URI("https://www.epicgames.com/site/en-US/privacypolicy?lang=en-US&sessionInvalidated=true"));

            }catch (IOException e){
                System.out.println("123");

            }
        }catch (URISyntaxException e){
            System.out.println("456");

        }
    }

    /**
     * 点击Service 的 hyperlink后，跳出网页进入服务说明。
     */
    public void openService(){

        try{
            try {
                Desktop.getDesktop().browse(new URI("https://www.epicgames.com/site/en-US/tos?lang=en-US"));

            }catch (IOException e){
                System.out.println("123");

            }
        }catch (URISyntaxException e){
            System.out.println("456");

        }
    }

}


