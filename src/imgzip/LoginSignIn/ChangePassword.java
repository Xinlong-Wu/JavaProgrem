package imgzip.LoginSignIn;

import imgzip.mainpane.Pane_sceenbeginner;
import imgzip.LoginSignIn.ChangePasswordController;
import imgzip.mainpane.Personal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.ResultSet;

/**
 @Author:   肖尧
 @Date: 2019.12.12

 改变密码的页面类，写成构造方法形式被调用。
 */
public class ChangePassword {


    public ChangePassword(int judgeIsLogined){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChangePassword.fxml"));

            PasswordField checkPassword = (PasswordField)root.lookup("#checkpassword");
            Button change = (Button)root.lookup("#changeThePassword");
            change.setDisable(true);

            change.setOnAction(e1->{

                String a = GlobalStringManager.getAccount();
                DataBaseController changeInstruction = new DataBaseController();
                ResultSet rs = null;

                try{

                    String encryptedPassword = HashUtil.hash(checkPassword.getText());
                    String currentInstruction = "update login set pwd = " + "'" + encryptedPassword + "'" + "where userName = "+ "'" + a.trim() + "'";
                    changeInstruction.queryUpdate(currentInstruction);

                    String currentInstruction2 = "SELECT pwd FROM login WHERE userName=" + "'" + a.trim() + "'";
                    rs = changeInstruction.queryExcecute(currentInstruction2);

                    if(rs.next()){

                        Stage stage = (Stage)checkPassword.getScene().getWindow();
                        new ChangePasswordSuccessfully(judgeIsLogined);
                        stage.close();
                    }else {
                        System.out.println("123");
                    }

                }catch (Exception e){
                    e.printStackTrace();

                }finally {
                    changeInstruction.close();

                }

            });

            Label different = (Label)root.lookup("#checkThePassword");
            different.setVisible(false);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 607, 500));
            primaryStage.show();



            primaryStage.setOnCloseRequest(e->{
                if(judgeIsLogined == 0){
                    new LoginBeginner();
                }else if(judgeIsLogined == 1){
                    new Pane_sceenbeginner();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void upDatePassword(){


    }
}

/**
 @Author:   肖尧
 @Date: 2019.12.12

 提醒用户成功改变密码的页面类，写成构造方法形式被调用。
 */

class ChangePasswordSuccessfully{

    public ChangePasswordSuccessfully(int judgeIsLogined){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChangePasswordSuccessfully.fxml"));
            Button SignUp = (Button)root.lookup("#signUp");

            if (judgeIsLogined == 0){
                SignUp.setText("Sign up now!");
                SignUp.setOnAction( e->{

                    Stage stage = (Stage) SignUp.getScene().getWindow();
                    new LoginBeginner();
                    stage.close();

                });
            }else {
                SignUp.setText("Back to HomePage!");
                SignUp.setOnAction( e->{

                    Stage stage = (Stage) SignUp.getScene().getWindow();
                    new Personal();
                    stage.close();
                });
            }


            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 638, 400));
            primaryStage.show();

            if (judgeIsLogined == 0){
                primaryStage.setOnCloseRequest(e->{
                    new LoginBeginner();
                });
            }else {
                primaryStage.setOnCloseRequest(e->{
                    new Pane_sceenbeginner();
                });
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
