package imgzip.mainpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 @Author:  吴泳仪
 @Date: 2019.12.17
 */
public class Change_email {
    /**
     加载更改邮箱的页面
     */
    public Change_email(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Change_email.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("修改用户名");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.getIcons().add(new Image("res/icon/logo.png"));
            primaryStage.show();
            primaryStage.getIcons().add(new Image("res/icon/logo.ico"));
            primaryStage.setOnCloseRequest(e->{
                new Personal();
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
