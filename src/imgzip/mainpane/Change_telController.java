package imgzip.mainpane;

import imgzip.LoginSignIn.DataBaseController;
import imgzip.LoginSignIn.GlobalStringManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Change_telController implements Initializable {
    public String b;
    @FXML
    private Button certain;
    @FXML
    private TextField text;

    public void initialize(URL lacation, ResourceBundle resources){

//        if(a.length() != 0) {
//            certain.setDisable(true);
//
//        }else{
//            certain.setDisable(false);
//        }
        certain.setOnAction(e -> {
            b = text.getText();
//            GlobalStringManager.setEmial(b);
            DataBaseController loginInstruction = new DataBaseController();
            ResultSet rs = null;
            String currentInstruction = "UPDATE login SET tel=" + "'" + b + "'" + "WHERE username=" + "'" + GlobalStringManager.getAccount() + "'";
            loginInstruction.queryUpdate(currentInstruction);
            loginInstruction.close();
            GlobalStringManager.setEmial(b);
            new Personal();
            Stage stage = (Stage) certain.getScene().getWindow();
            stage.close();
        });

    }
}
