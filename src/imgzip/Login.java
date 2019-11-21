package imgzip;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 @Author:   肖尧
 @Date: 2019.11.20
 */
public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

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
