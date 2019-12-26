package imgzip.mainpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Pane_sceenbeginner {
    /**
     别的页面在切换到主页面时可调用的创建主页面的方法
     */
    public Pane_sceenbeginner(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Pane_sceen.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Homepage");
            primaryStage.setScene(new Scene(root, 939, 685));
            primaryStage.getIcons().add(new Image("res/icon/logo.png"));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
