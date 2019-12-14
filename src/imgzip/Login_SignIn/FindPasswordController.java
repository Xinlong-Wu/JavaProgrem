package imgzip.Login_SignIn;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;

import java.awt.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FindPasswordController {

    @FXML
    private TextField account;

    @FXML
    private TextField email;

    @FXML
    private Button verify;

    boolean accountIsEmpty = true;
    public void checkAccountIsEmpty(){

        if("".equals(account.getText())){
            accountIsEmpty = false;

            if(!verify.isDisable()){
                verify.setDisable(true);
            }

        }else {
            accountIsEmpty = false;
            if(!emailIsEmpty && verify.isDisable()){
                verify.setDisable(false);
            }
        }
    }

    boolean emailIsEmpty = true;
    public void checkEmailIsEmpty(){

        if("".equals(email.getText())){
            emailIsEmpty = true;

            if(!verify.isDisable()){
                verify.setDisable(true);
            }

        }else {
            emailIsEmpty = false;
            if(!accountIsEmpty && verify.isDisable()){
                verify.setDisable(false);
            }
        }
    }

    public void verifyEmailAndAccount(){

    DataBaseController verifyInstruction = new DataBaseController();
    ResultSet rs = null;

    try{

        String currentInstruction = "SELECT email FROM login WHERE userName=" + "'" + account.getText().trim() + "'";
        rs = verifyInstruction.queryExcecute(currentInstruction);

        if(rs.next()){

            if(rs.getString(1).equals(email.getText())){

                GlobalStringManager.setAccount(account.getText());
                new ChangePassword();
                Stage stage = (Stage)account.getScene().getWindow();
                stage.close();

            }else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("We are sorry to inform you that:");
                alert.setContentText("This email is WRONG!");

                alert.showAndWait();

            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("We are sorry to inform you that:");
            alert.setContentText("This Account doesn't exist!");

            alert.showAndWait();
        }


    }catch (Exception e){


    }finally {
        verifyInstruction.close();
    }

    }


    public void backToSignIn(){
        Stage stage = (Stage) verify.getScene().getWindow();
        new LoginBeginner();
        stage.close();
    }
}




