package imgzip.mainpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 @Author:  吴泳仪
 @Date: 2019.12.10
 */
public class Course {
    public Course(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Course.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Course");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Course(int a){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Course(none).fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Course");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
