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
public class Main extends Application {
    static private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        MainBox scene = new MainBox();
        Main.primaryStage.setTitle("图像压缩处理");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.setResizable(false);
        Main.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void close(){
        Main.primaryStage.close();
    }
}
