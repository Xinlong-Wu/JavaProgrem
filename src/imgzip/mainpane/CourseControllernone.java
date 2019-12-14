package imgzip.mainpane;

import com.sun.org.apache.xalan.internal.xsltc.dom.CurrentNodeListFilter;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseControllernone implements Initializable {
    @FXML
    private Button coursenone;
    @FXML
    private Button pricenone;
    @FXML
    private Button loginnone;
    @FXML
    private Button homepagenone;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Button forget;
    @FXML
    private Button change;
    @FXML
    private Button compress;
    @FXML
    private BorderPane page;


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
        login.setOnAction(e -> {
//            page.getChildren().clear();
//            Image image = new Image("pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });
        register.setOnAction(e -> {
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });
        forget.setOnAction(e->{
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });
        change.setOnAction( e-> {
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });
        compress.setOnAction( e-> {
//            page.getChildren().clear();
//            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            page.setCenter(imageView);
        });
    }
}
