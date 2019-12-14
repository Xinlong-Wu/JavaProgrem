package imgzip.mainpane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FunctionpageController implements Initializable {
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private AnchorPane change;

    public void initialize(URL lacation, ResourceBundle resources){
        /**
         本页面连接到course
         */
        course.setOnAction(e -> {
            new Course();
            Stage stage = (Stage)course.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到price
         */
        price.setOnAction(e -> {
            new Pricepane();
            Stage stage = (Stage)price.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到homepage
         */
        signout.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)signout.getScene().getWindow();
            stage.close();
        });

    }
}
