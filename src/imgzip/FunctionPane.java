package imgzip;

import imgzip.mainwindow.MainBox;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * AlertWindow class
 *
 * @author 乌鑫龙
 * @date 2019/12/3
 */
public class FunctionPane{
    static private Stage primaryStage;
    public FunctionPane() {
        primaryStage = new Stage();
        MainBox scene = new MainBox();
        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void close(){
        primaryStage.close();
    }
}

