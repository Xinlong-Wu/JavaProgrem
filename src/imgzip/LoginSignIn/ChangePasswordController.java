package imgzip.LoginSignIn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;

/**
 @Author:   肖尧
 @Date: 2019.12.12
 */
public class ChangePasswordController {

    @FXML
    TextField password = new TextField();

    @FXML
    TextField checkPassword = new TextField();

    @FXML
    Label checkThePassword = new Label();

    @FXML
    Button changeThePassword = new Button();


    /**
     * 点击按钮后，更改密码，并检查是否更改成功。
     */

    public void upDatePassword(){

        String a = GlobalStringManager.getAccount();
        DataBaseController changeInstruction = new DataBaseController();
        ResultSet rs = null;

        try{

            String currentInstruction = "update login set pwd = " + "'" + checkPassword.getText().trim() + "'" + "where userName = "+ "'" + a.trim() + "'";
            changeInstruction.queryUpdate(currentInstruction);

            String currentInstruction2 = "SELECT pwd FROM login WHERE userName=" + "'" + a.trim() + "'";
            rs = changeInstruction.queryExcecute(currentInstruction2);

            if(rs.next()){

                Stage stage = (Stage) password.getScene().getWindow();
                new ChangePasswordSuccessfully();
                stage.close();
            }else {


                System.out.println("123");
            }


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            changeInstruction.close();

        }
    }

    /**
     * 检查第一次输入的密码和第二次输入的密码是否相同，以及两个密码框是否为空。
     * 如果相同或者为空，则无法点击按钮，并会有提示。
     */

    public void checkPasswordTheSame(){

        if("".equals(password.getText()) || "".equals(checkPassword.getText())){

            if(!changeThePassword.isDisable()){
                changeThePassword.setDisable(true);
            }

            if(checkThePassword.isVisible()){
                checkThePassword.setVisible(false);
            }


        }else{

            if(password.getText().equals(checkPassword.getText())){
                if(changeThePassword.isDisable()){
                    changeThePassword.setDisable(false);
                }

                if(checkThePassword.isVisible()){
                    checkThePassword.setVisible(false);
                }

            }

            if(!password.getText().equals(checkPassword.getText())){

                if(!changeThePassword.isDisable()){
                    changeThePassword.setDisable(true);
                }

                if(!checkThePassword.isVisible()){
                    checkThePassword.setVisible(true);
                }
            }

        }

    }


}
