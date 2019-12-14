package imgzip.Login_SignIn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoginBeginner {
    public LoginBeginner(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

            TextField account = (TextField)root.lookup("#Account");
            PasswordField password = (PasswordField)root.lookup("#password");
            CheckBox remember = (CheckBox)root.lookup("#remember");
            Button logIn = (Button)root.lookup("#Login");


            /**
             在页面开启之前检查上一次登录时是否记住了密码，
             如果上一次登录点击了记住密码，则在本次打开时会将账号和密码写入
             */
            try {

                FileReader fr = new FileReader("src/txtFile/RememberAccount&Password.txt");
                BufferedReader br = new BufferedReader(fr);

                String line = "";
                line = br.readLine();


                String[] judge = line.split("\\|");
                String truejudge = "true";
                int accountIndex = 0;
                int passwordIndex = 1;
                int judgeIndex = 2;

                if(judge[judgeIndex].equals(truejudge) ){
                    account.setText(judge[accountIndex]);
                    password.appendText(judge[passwordIndex]);

                    remember.setSelected(true);
                    logIn.setDisable(false);

                }

                br.close();
                fr.close();

            }catch (Exception e){
                e.printStackTrace();

            }

            primaryStage.setTitle("XXX login");
            primaryStage.setScene(new Scene(root, 638, 400));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
