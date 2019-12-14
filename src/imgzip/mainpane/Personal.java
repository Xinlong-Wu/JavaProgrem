package imgzip.mainpane;

import imgzip.Login_SignIn.DataBaseController;
import imgzip.Login_SignIn.GlobalStringManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
            TextArea account = (TextArea)root.lookup("#accountname");
            TextArea email = (TextArea)root.lookup("#password");
            TextArea tel = (TextArea)root.lookup("#tel");

            DataBaseController loginInstruction = new DataBaseController();
            ResultSet rs = null;
            try{
                String currentInstruction = "SELECT * FROM login WHERE userName=" + "'" + GlobalStringManager.getAccount()+ "'";
                rs = loginInstruction.queryExcecute(currentInstruction);


                if (rs.next()) {
                    account.setText(rs.getString(1));
                    email.setText(rs.getString(3));
                    tel.setText(rs.getString(4));

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
