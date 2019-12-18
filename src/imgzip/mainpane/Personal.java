package imgzip.mainpane;

import imgzip.LoginSignIn.DataBaseController;
import imgzip.LoginSignIn.GlobalStringManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 @Author:  吴泳仪
 @Date: 2019.12.14
 */
//class Personalbeginner extends Application {
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        try {
//            Parent root=FXMLLoader.load(getClass().getResource("/fxml/Personal.fxml"));
//            primaryStage.setTitle("Homepage");
//            primaryStage.setScene(new Scene(root, 939, 685));
//            primaryStage.show();
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
public class Personal {


    public Personal(){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Personal.fxml"));

            Pane accountp = (Pane)root.lookup("#accountp");
            Pane emailp = (Pane)root.lookup("#emailp");
            Pane telp= (Pane)root.lookup("#telp");

            DataBaseController loginInstruction = new DataBaseController();
            ResultSet rs = null;
            try{
                String currentInstruction = "SELECT * FROM login WHERE userName=" + "'" +GlobalStringManager.getAccount()+ "'";
                rs = loginInstruction.queryExcecute(currentInstruction);

                if (rs.next()) {

                    Label a = new Label(rs.getString(1));
                    a.setStyle("-fx-font-family: 'FangSong';-fx-font-size: 20;");
                    accountp.getChildren().add(a);
                    GlobalStringManager.setAccount(rs.getString(1));

                    Label e = new Label(rs.getString(3));
                    e.setStyle("-fx-font-family: 'FangSong';-fx-font-size: 20;");
                    emailp.getChildren().add(e);
                    GlobalStringManager.setEmial(rs.getString(3));

                    Label t = new Label(rs.getString(4));
                    t.setStyle("-fx-font-family: 'FangSong';-fx-font-size: 20;");
                    telp.getChildren().add(t);
                }


            }catch (SQLException e){
                e.printStackTrace();

            }finally {
                loginInstruction.close();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Personal");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
