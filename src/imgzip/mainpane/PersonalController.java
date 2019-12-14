package imgzip.mainpane;

import imgzip.FunctionPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 @Author:  吴泳仪
 @Date: 2019.12.14
 */
public class PersonalController implements Initializable {
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private Button functionpage;
    public void initialize(URL lacation, ResourceBundle resources){
        /**
         本页面连接到homepage
         */
        signout.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)signout.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到course
         */
        course.setOnAction(e -> {
            new Course(1);
            Stage stage = (Stage)course.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到price
         */
        price.setOnAction(e->{
            new Pricepane(1);
            Stage stage = (Stage)price.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到functionpage
         */
        functionpage.setOnAction(e -> {
            new FunctionPane();
            Stage stage = (Stage)functionpage.getScene().getWindow();
            stage.close();
        });
    }
}
