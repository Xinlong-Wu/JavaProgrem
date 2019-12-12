package mainpane;

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

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    private Label homepage;
    @FXML
    private Line line;
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private Button functionpage;
    @FXML
    private Button signin;
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
        signout.setOnAction(e -> {

        });
        course.setOnAction(e -> {

        });
        price.setOnAction(e->{

        });
        functionpage.setOnAction(e -> {

        });
        signin.setOnAction(e -> {
            page.getChildren().clear();
            Image image = new Image("pkq.gif");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            page.setCenter(imageView);
        });
        register.setOnAction(e -> {
            page.getChildren().clear();
            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            page.setCenter(imageView);
        });
        forget.setOnAction(e->{
            page.getChildren().clear();
            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            page.setCenter(imageView);
        });
        change.setOnAction( e-> {
            page.getChildren().clear();
            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            page.setCenter(imageView);
        });
        compress.setOnAction( e-> {
            page.getChildren().clear();
            Image image = new Image("C:\\Users\\24472\\Desktop\\image\\pkq.gif");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            page.setCenter(imageView);
        });
    }
}
