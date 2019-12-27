package imgzip.LoginSignIn;

import imgzip.mainpane.Pane_sceenbeginner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.regex.*;

import java.sql.ResultSet;

/**
 @Author:   肖尧
 @Date: 2019.12.4

 提供用户创造密码的页面类，通过调用构造方法创建。
 */

public class CreateAccount {
    boolean judgeAccountExists = false;
    boolean judgeEmialexist = false;

    public CreateAccount(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateAccount.fxml"));

            /**
             * already:提醒用户账号是否正确文字
             * already2:提醒用户邮箱是否正确
             * already3:提醒用户密码的安全等级，以及各式是否合法。
             */
            Label already = (Label)root.lookup("#Al1");
            Label already2 = (Label)root.lookup("#Al2");
            Label already3 = (Label)root.lookup("#Al3");

            Button createAccount = (Button)root.lookup("#createAccount");
            TextField userName = (TextField)root.lookup("#userName");
            TextField email = (TextField)root.lookup("#email");
            PasswordField password = (PasswordField) root.lookup("#passWord");
            CheckBox agree = (CheckBox)root.lookup("#agree");

            already.setVisible(false);
            already2.setVisible(false);
            already3.setVisible(false);
            createAccount.setDisable(true);



            /**
             * change方法：当account输入框失去/得到焦点时，检查一次是否与数据库中的某个账号重复
             * 1：保证账号的唯一性。
             * 2：当账号非法时，显示提醒字符提醒用户。
             * 3：当账号由非法变为合法时，如果邮箱、密码、是否点击了解服务条款都符合了条件，注册账号按钮才会被激活
             * 否则不激活
             */
            userName.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if(checkIfIsAccount(userName.getText())){

                        if("Invalid account!".equals(already.getText())){
                            already.setText("Already taken!");
                        }

                        if (already.isVisible() || "".equals(userName.getText())){
                            already.setVisible(false);
                        }

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

                                    if(!judgeEmialexist && agree.isSelected() &&!"".equals(password.getText())){
                                        createAccount.setDisable(false);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            createAccountInstruction.close();
                        }

                    }else {

                        if("Already taken!".equals(already.getText())){
                            already.setText("Invalid account!");
                        }

                        if("".equals(userName.getText())){
                            already.setVisible(false);
                            if(!createAccount.isDisable()){
                                createAccount.setDisable(true);
                            }

                        }else {

                            if (!already.isVisible()){
                                already.setVisible(true);
                            }

                            if (!createAccount.isDisable()){
                                createAccount.setDisable(true);
                            }
                        }
                    }
                }
            });




            /**
                email的change方法：
                当email输入框失去\获得焦点时，检查email框输入的值是否符合邮箱格式，以及是否已经在数据库中存在。
                保证格式正确以及数据库中数据唯一性。
             */
            email.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if(checkIfIsEmail(email.getText())){

                        if("Invalid email!".equals(already2.getText())){
                            already2.setText("Already taken!");
                        }

                        if (already2.isVisible() || "".equals(email.getText())){
                            already2.setVisible(false);
                        }

                        DataBaseController createAccountInstruction = new DataBaseController();
                        ResultSet rs = null;
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

                                    if(!judgeAccountExists && agree.isSelected() && !"".equals(password.getText()) && !already3.getText().equals("Invalid!") &&!already.isVisible() && !already2.isVisible()  ){
                                        createAccount.setDisable(false);

                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        } finally {
                            createAccountInstruction.close();
                        }
                    }else{
                         if("Already taken!".equals(already2.getText())){
                             already2.setText("Invalid email!");
                         }

                         if("".equals(email.getText())){
                             already2.setVisible(false);
                             if(!createAccount.isDisable()){
                                 createAccount.setDisable(true);
                             }

                         }else {

                            if (!already2.isVisible()){
                                already2.setVisible(true);
                            }

                            if (!createAccount.isDisable()){
                                createAccount.setDisable(true);
                            }
                         }
                    }
                }
            });


            /**
             * 检查密码格式是否正确：
             * ①密码为空，无法点击注册按钮
             * ②密码为非法密码，无法点击注册按钮（根据同行的label是否显示“invalid”来判断
             * ③当密码合法，且邮箱账号都合法时，跳转焦点才会使得注册按钮可被点击。当密码合法，
             * 但是其他的条件不合法时，切换焦点依然无法激活注册按钮
             */
            password.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if("".equals(password.getText())){

                        if (!createAccount.isDisable()){
                            createAccount.setDisable(true);
                        }
                        already3.setVisible(false);

                    }else {

                        if (!already.isVisible() && !already2.isVisible() && !"".equals(userName.getText()) && !"".equals(email.getText()) && agree.isSelected() && !already3.getText().equals("Invalid!")){
                            createAccount.setDisable(false);
                        }else {
                            createAccount.setDisable(true);
                        }

                    }
                }
            });

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create an Account");
            primaryStage.setScene(new Scene(root, 600.0000999999975, 633));
            primaryStage.show();
            primaryStage.getIcons().add(new Image("res/icon/logo.ico"));
            primaryStage.setOnCloseRequest(e->{
                new Pane_sceenbeginner();
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 检查邮箱格式是否正确.
     */
    public boolean checkIfIsEmail(String email){

        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();

        return isMatched;
    }

    /**
     * 检查账号格式是否正确.
     */
    public boolean checkIfIsAccount(String account){

        String check = "^[a-zA-Z0-9_]{4,15}$";

        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(account);
        boolean isMatched = matcher.matches();

        return isMatched;
    }



}

/**
 @Author:   肖尧
 @Date: 2019.12.12

 提醒用户成功改变密码的页面类，写成构造方法形式被调用。
 */

class CreateSuccessfully{

    public CreateSuccessfully(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateSuccessfully.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create an Account Successfully!");
            primaryStage.setScene(new Scene(root, 638, 406));
            primaryStage.show();
            primaryStage.getIcons().add(new Image("res/icon/logo.ico"));
            primaryStage.setOnCloseRequest(e->{
                new LoginBeginner();
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

