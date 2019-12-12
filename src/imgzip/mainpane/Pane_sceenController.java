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
		register.setOnAction(e -> {

		});
		signin.setOnAction(e->{

		});
		course.setOnAction(e -> {
			new Course();
			Stage stage = (Stage)course.getScene().getWindow();
			stage.close();
		});
		price.setOnAction(e -> {
			new Pricepane();
			Stage stage = (Stage)price.getScene().getWindow();
			stage.close();
		});
	}

}
