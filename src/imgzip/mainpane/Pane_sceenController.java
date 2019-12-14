package imgzip.mainpane;

import imgzip.Login_SignIn.CreateAccount;
import imgzip.Login_SignIn.Login;
import imgzip.Login_SignIn.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 @Author: 吴泳仪
 @Date: 2019.12.9
 */
public class Pane_sceenController implements Initializable {
	@FXML
	private Label homepage;
	@FXML
	private Line line;
	@FXML
	private Button register;
	@FXML
	private Button signin;
	@FXML
	private Button course;
	@FXML
	private Button price;
	@FXML
	private Pane changepane;

	public void initialize(URL lacation, ResourceBundle resources){
		/**
		 本页面连接到注册页面
		 */
		register.setOnAction(e -> {
			new CreateAccount();
			Stage stage = (Stage)register.getScene().getWindow();
			stage.close();
		});

		/**
		 本页面连接到登录页面
		 */
		signin.setOnAction(e->{
			Stage stage = (Stage)signin.getScene().getWindow();
			new LoginBeginner();
			stage.close();
		});

		/**
		 本页面连接到course未登录页面
		 */
		course.setOnAction(e -> {
			new Course();
			Stage stage = (Stage)course.getScene().getWindow();
			stage.close();
		});

		/**
		 本页面连接到price未登录页面
		 */
		price.setOnAction(e -> {
			new Pricepane(1);
			Stage stage = (Stage)price.getScene().getWindow();
			stage.close();
		});
	}

}
