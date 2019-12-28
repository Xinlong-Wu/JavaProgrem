package imgzip.mainpane;

import imgzip.LoginSignIn.DataBaseController;
import imgzip.LoginSignIn.GlobalStringManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 @Author:  吴泳仪
 @Date: 2019.12.14
 */
public class Personal {


    public Personal(){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Personal.fxml"));

            //Personal的fxml中的pane组件
            Pane accountp = (Pane)root.lookup("#accountp");
            Pane emailp = (Pane)root.lookup("#emailp");
            Pane telp= (Pane)root.lookup("#telp");
            TextArea pic= (TextArea) root.lookup("#pic");

            //新建一个DataBaseController类的实体
            DataBaseController loginInstruction = new DataBaseController();
            ResultSet rs = null;
            try{
                //用于执行的sql语句，选择用户名为登录时用户名的账户的所有信息
                String currentInstruction = "SELECT * FROM login WHERE userName=" + "'" +GlobalStringManager.getAccount()+ "'";
                rs = loginInstruction.queryExcecute(currentInstruction);

                if (rs.next()) {
                    //把从数据库中读出来的用户名利用label显示在页面上
                    //并把用户名保存到GlobalStringManager中
                    Label a = new Label(rs.getString(1));
                    a.setStyle("-fx-font-family: 'FangSong';-fx-font-size: 20;");
                    accountp.getChildren().add(a);
                    GlobalStringManager.setAccount(rs.getString(1));
                    //把从数据库中读出来的email利用label显示在页面上
                    //并把email保存到GlobalStringManager中
                    Label e = new Label(rs.getString(3));
                    e.setStyle("-fx-font-family: 'FangSong';-fx-font-size: 20;");
                    emailp.getChildren().add(e);
                    GlobalStringManager.setEmial(rs.getString(3));
                    //把从数据库中读出来的电话号码利用label显示在页面上
                    Label t = new Label(rs.getString(4));
                    t.setStyle("-fx-font-family: 'FangSong';-fx-font-size: 20;");
                    telp.getChildren().add(t);
                    //把从GlobalStringManager中读出来的图片序列利用label显示在页面上

                    ArrayList<String> sequence = new ArrayList<>();
                    sequence = GlobalStringManager.getPicSequences();
                    for(int i = 0 ; i < GlobalStringManager.getPicSequences().size();i++){
//                        pic.appendText(sequence.get(i));
                    }

                }

            }catch (SQLException e){
                e.printStackTrace();

            }finally {
                loginInstruction.close();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Personal");
            Scene scene = new Scene(root, 939, 685);
            scene.getStylesheets().add("css/mainpanecss.css");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("res/icon/logo.ico"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
