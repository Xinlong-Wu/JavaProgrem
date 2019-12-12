package imgzip.mainpane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import java.net.URL;
import java.util.ResourceBundle;

/**
 @Author:吴泳仪
 @Date:2019.12.09
 */
public class PricepaneController implements Initializable {
    @FXML
    private Label logo;
    @FXML
    private Line line;
    @FXML
    private Button functionpage;
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    public void initialize(URL lacation, ResourceBundle resources){
        functionpage.setOnAction(e -> {

        });
        course.setOnAction(e -> {

        });
        price.setOnAction(e -> {

        });
        signout.setOnAction(e -> {

        });
    }
}
