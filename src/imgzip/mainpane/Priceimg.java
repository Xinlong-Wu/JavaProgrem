package imgzip.mainpane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 @Author:  吴泳仪
 @Date: 2019.12.18
 */
public class Priceimg {
    /**
     加载显示微信收费的页面
     */
    public Priceimg(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Priceimg.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("pay");
            primaryStage.setScene(new Scene(root, 237, 327));
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}