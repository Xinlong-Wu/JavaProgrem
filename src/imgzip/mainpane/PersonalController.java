package imgzip.mainpane;

import imgzip.FunctionPane;
import imgzip.LoginSignIn.ChangePassword;
import imgzip.LoginSignIn.GlobalStringManager;
import imgzip.alertwindow.AlertWindow;
import imgzip.mainwindow.FunctionBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 @Author:  吴泳仪
 @Date: 2019.12.14
 */
public class PersonalController implements Initializable {
    @FXML
    private Button course;
    @FXML
    private Button price;
    @FXML
    private Button signout;
    @FXML
    private Button functionpage;
    @FXML
    private Button changeemail;
    @FXML
    private Button changetel;
    @FXML
    private Button changepw;
    @FXML
    private VBox codeList;
    @FXML
    private ScrollPane scoller;

    @Override
    public void initialize(URL lacation, ResourceBundle resources){

        /**
         本页面连接到homepage
         */
        signout.setOnAction(e -> {
            new Pane_sceenbeginner();
            Stage stage = (Stage)signout.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到course
         */
        course.setOnAction(e -> {
            new Course();
            Stage stage = (Stage)course.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到price
         */
        price.setOnAction(e->{
            new Pricepane();
            Stage stage = (Stage)price.getScene().getWindow();
            stage.close();
        });

        /**
         本页面连接到functionpage
         */
        functionpage.setOnAction(e -> {
            new FunctionPane();
            Stage stage = (Stage)functionpage.getScene().getWindow();
            stage.close();
        });


        /**
         加载修改邮箱的页面Change_email
         */
        changeemail.setOnAction(e -> {
            new Change_email();
            Stage stage = (Stage) changeemail.getScene().getWindow();
            stage.close();
        });

        /**
         加载修改电话号的页面Change_tel
         */
        changetel.setOnAction(e -> {
            new Change_tel();
            Stage stage = (Stage) changetel.getScene().getWindow();
            stage.close();
        });

        /**
         加载修改密码的页面
         */
        changepw.setOnAction(e -> {
            new ChangePassword(1);
            Stage stage = (Stage)changepw.getScene().getWindow();
            stage.close();
        });

        /**
         * @Author:乌鑫龙
         * 加载用户上传的图片提取码
         */
        codeList.getChildren().addAll(getCodes());
    }

    /**
     * @Author:乌鑫龙
     * 加载用户上传的图片提取码
     */
    private ArrayList<Button> getCodes(){
        ArrayList<Button> btCodes = new ArrayList<>();
        ArrayList<String> codes = GlobalStringManager.getPicSequences();
        codes.forEach(code->{
            Button btCode = new Button(code);
            btCode.setOnAction(e->{
                FunctionBox.setclipboardtext(code);
                AlertWindow alertWindow = new AlertWindow("复制成功","提取码已复制到您的粘贴板上",500);
                alertWindow.start(new Stage());
            });
            btCode.setStyle("-fx-background-color: white;-fx-pref-height: 10px;-fx-text-fill:#6f6bff");
            btCode.setPrefWidth(345);
            btCode.setAlignment(Pos.CENTER_LEFT);
            btCode.setCursor(Cursor.HAND);
            btCode.setOnMouseEntered(e->{
                btCode.setStyle("-fx-background-color: #F2F2F2;-fx-text-fill:blue;-fx-font-weight: bold;");
            });
            btCode.setOnMouseExited(e->{
                btCode.setStyle("-fx-background-color: white;-fx-text-fill:#6f6bff;-fx-font-weight: normal");
            });
            btCodes.add(btCode);
        });
        return btCodes;
    }
}
