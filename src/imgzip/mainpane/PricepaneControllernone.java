package imgzip.mainpane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 @Author:吴泳仪
 @Date:2019.12.09
 */
public class PricepaneControllernone implements Initializable {
    @FXML
    private Button coursenone;
    @FXML
    private Button pricenone;
    @FXML
    private Button loginnone;
    @FXML
    private Button homepagenone;
    public void initialize(URL lacation, ResourceBundle resources){
        coursenone.setOnAction(e -> {
            new Course(1);
            Stage stage = (Stage)coursenone.getScene().getWindow();
            stage.close();
        });
        pricenone.setOnAction(e -> {
            new Pricepane(1);
            Stage stage = (Stage)pricenone.getScene().getWindow();
            stage.close();
        });
        loginnone.setOnAction(e -> {

        });
        homepagenone.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)homepagenone.getScene().getWindow();
            stage.close();
        });
    }
}
