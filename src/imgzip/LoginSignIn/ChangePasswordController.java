package imgzip.LoginSignIn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 @Author:   肖尧
 @Date: 2019.12.28
 */
public class ChangePasswordController {


    @FXML
    TextField passWord = new TextField();

    @FXML
    TextField checkPassword = new TextField();

    @FXML
    Label checkThePassword = new Label();

    @FXML
    Label strength = new Label();

    @FXML
    Button changeThePassword = new Button();


    /**
     * 点击按钮后，更改密码，并检查是否更改成功。
     */

//    public void upDatePassword(){
//
//        String a = GlobalStringManager.getAccount();
//        DataBaseController changeInstruction = new DataBaseController();
//        ResultSet rs = null;
//
//        try{
//
//            String encryptedPassword = HashUtil.hash(checkPassword.getText());
//            String currentInstruction = "update login set pwd = " + "'" + encryptedPassword + "'" + "where userName = "+ "'" + a.trim() + "'";
//            changeInstruction.queryUpdate(currentInstruction);
//
//            String currentInstruction2 = "SELECT pwd FROM login WHERE userName=" + "'" + a.trim() + "'";
//            rs = changeInstruction.queryExcecute(currentInstruction2);
//
//            if(rs.next()){
//
//                Stage stage = (Stage) password.getScene().getWindow();
//                new ChangePasswordSuccessfully();
//                stage.close();
//            }else {
//
//
//                System.out.println("123");
//            }
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }finally {
//            changeInstruction.close();
//
//        }
//    }
    /**
     * 检查当前的密码安全度：
     * ①当前密码非法时，Label显示"Invalid"且为红色
     * ②当前密码为弱密码时，lable显示"Weak Passoword"且为红色
     * ③当前密码为中等强度密码时，label显示"Medium Passoword"且为黄色
     * ④当前密码为强密码时，lable显示"Strong Password"且为绿色
     */
    public void checkTheStrengtenOfPassowrd(){
        System.out.println(checkTheStengthOfPassword(passWord.getText()));
        if (!"".equals(passWord.getText())){

            if(checkTheStengthOfPassword(passWord.getText()) == 0){
                strength.setText("Invalid!");
                strength.setTextFill(Color.RED);
                strength.setVisible(true);
                changeThePassword.setDisable(true);

            }else if (checkTheStengthOfPassword(passWord.getText()) == 1){
                strength.setText("Weak Password");
                strength.setTextFill(Color.RED);
                strength.setVisible(true);

            }else if(checkTheStengthOfPassword(passWord.getText()) == 2){
                strength.setText("Medium Password");
                strength.setTextFill(Color.YELLOW);
                strength.setVisible(true);
            }else if (checkTheStengthOfPassword(passWord.getText()) == 3){
                strength.setText("Strong Password");
                strength.setTextFill(Color.GREEN);
                strength.setVisible(true);
            }

        }else {
            if (strength.isVisible()){
                strength.setVisible(false);
                changeThePassword.setDisable(true);
            }
        }
    }


    /**
     *
     * @param password
     * @return int
     * 检查密码格式： ①长度为5-30位，小于5位或大于30位或使用了特殊字符都会坚定为非法密码
     *               ②当长度大于5，小于8，且符合一级密码格式的密码，界定为弱安全密码
     *               ③当长度大于8，符合一级密码格式但是不符合二级强密码格式的密码，界定为中等安全密码
     *               ④当长度大于8，符合一级、二级密码格式的密码，界定为强安全密码。
     */

    public int checkTheStengthOfPassword(String password){

        String strongFormula = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,30}$";
        String weakFormula = "[a-zA-Z0-9]\\w{5,30}";

        Pattern weakPattern = Pattern.compile(weakFormula);
        Pattern strongPattern = Pattern.compile(strongFormula);

        Matcher weakMatcher = weakPattern.matcher(password);
        Matcher strongMatcher = strongPattern.matcher(password);


        if(weakMatcher.matches()){
            if (strongMatcher.matches()){
                return 3;
            }else {

                if (password.length() <= 8){
                    return 1;
                }else {

                    return 2;
                }
            }
        }else {
            return 0;
        }
    }



    /**
     * 检查第一次输入的密码和第二次输入的密码是否相同，以及两个密码框是否为空。
     * 如果不相同或者为空，则无法点击按钮，并会有提示。
     */

    public void checkPasswordTheSame(){

        if("".equals(passWord.getText()) || "".equals(checkPassword.getText())){

            if(!changeThePassword.isDisable()){
                changeThePassword.setDisable(true);
            }

            if(checkThePassword.isVisible()){
                checkThePassword.setVisible(false);
            }

            if ("".equals(passWord.getText()) && "".equals(checkPassword.getText()) && checkThePassword.isVisible()){
                checkThePassword.setVisible(false);
            }


        }else{

            if(passWord.getText().equals(checkPassword.getText())){
                if(changeThePassword.isDisable() && !strength.getText().equals("Invalid!")){
                    changeThePassword.setDisable(false);
                }

                if(checkThePassword.isVisible()){
                    checkThePassword.setVisible(false);
                }

            }

            if(!passWord.getText().equals(checkPassword.getText())){

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
