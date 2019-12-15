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
import java.io.File;
import java.io.FileReader;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import java.util.regex.*;

import javafx.scene.control.Label;

import java.sql.ResultSet;

public class LoginBeginner {
    public LoginBeginner(){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

            TextField account = (TextField)root.lookup("#Account");
            PasswordField password = (PasswordField)root.lookup("#password");
            CheckBox remember = (CheckBox)root.lookup("#remember");
            Button logIn = (Button)root.lookup("#Login");
            Label passwordWrong = (Label)root.lookup("#passwordWrong");
            passwordWrong.setVisible(false);

            /**
             在页面开启之前检查上一次登录时是否记住了密码，
             如果上一次登录点击了记住密码，则在本次打开时会将账号和密码写入
             */
            try {

                File file = new File("src/txtFile/RememberAccount&Password.txt");
                if(!file.exists()){
                    file.createNewFile();
                }

                File file1 = new File("src/txtFile/RememberAccount&Password.txt");
                FileReader fr = new FileReader(file1);
                BufferedReader br = new BufferedReader(fr);

                String line = "";
                line = br.readLine();

                if(file1.length() != 0){

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
                }


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
