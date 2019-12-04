package imgzip;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 @Author:   肖尧
 @Date: 2019.12.4
 */
public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

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

    public static void main(String[] args) {
        launch(args);
    }

}


class Loginbeginner{
    public Loginbeginner(){
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