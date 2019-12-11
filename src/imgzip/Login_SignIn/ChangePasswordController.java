package imgzip.Login_SignIn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.misc.Resource;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ChangePasswordController {

    @FXML
    TextField password = new TextField();

    @FXML
    TextField checkPassword = new TextField();

    @FXML
    Label checkThePassword = new Label();

    public void upDatePassword(){


        String a = GlobalStringManager.getAccount();
        DataBaseController changeInstruction = new DataBaseController();
        ResultSet rs = null;

        try{

            String currentInstruction = "update login set pwd = " + "'" + checkPassword.getText().trim() + "'" + "where userName = "+ "'" + a.trim() + "'";
            changeInstruction.queryUpdate(currentInstruction);

            String currentInstruction2 = "SELECT pwd FROM login WHERE userName=" + "'" + a.trim() + "'";
            rs = changeInstruction.queryExcecute(currentInstruction2);

            if(rs.next()){

                Stage stage = (Stage) password.getScene().getWindow();
                new ChangePasswordSuccessfully();
                stage.close();
            }else {
                System.out.println("123");
            }


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            changeInstruction.close();

        }
    }

    public void checkPasswordTheSame(){








    }


}
