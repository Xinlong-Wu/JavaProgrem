package imgzip.LoginSignIn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 @Author:   肖尧
 @Date: 2019.12.12

 验证用户，决定用户是否可以改变密码的页面类，写成构造方法形式被调用。
 */
public class FindPassword {

    public FindPassword(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FindPassword.fxml"));


            Button verify = (Button)root.lookup("#verify");
            verify.setDisable(true);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Verify your ID");
            primaryStage.setScene(new Scene(root, 607, 500));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
