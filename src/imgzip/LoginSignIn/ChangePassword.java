package imgzip.LoginSignIn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 @Author:   肖尧
 @Date: 2019.12.12

 改变密码的页面类，写成构造方法形式被调用。
 */
public class ChangePassword {


    public ChangePassword(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChangePassword.fxml"));

            Button change = (Button)root.lookup("#changeThePassword");
            change.setDisable(true);

            Label different = (Label)root.lookup("#checkThePassword");
            different.setVisible(false);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 607, 500));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

/**
 @Author:   肖尧
 @Date: 2019.12.12

 提醒用户成功改变密码的页面类，写成构造方法形式被调用。
 */

class ChangePasswordSuccessfully{

    public ChangePasswordSuccessfully(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChangePasswordSuccessfully.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 638, 400));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
