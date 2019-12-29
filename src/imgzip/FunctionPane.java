package imgzip;

import imgzip.mainpane.Course;
import imgzip.mainpane.Personal;
import imgzip.mainwindow.FunctionBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * AlertWindow class
 *
 * @author 乌鑫龙
 * @date 2019/12/3
 */
public class FunctionPane{
    static private Stage primaryStage;
    static private FunctionBox scene;
    public FunctionPane() {
        primaryStage = new Stage();
        if(scene==null){
            scene = new FunctionBox();
        }
        primaryStage.setTitle("图像压缩处理");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("res/icon/logo.png"));
        primaryStage.setOnCloseRequest(e->{
            new Personal();
        });

    }

    public static void close(){
        primaryStage.close();
    }
}

