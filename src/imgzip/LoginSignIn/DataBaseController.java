package imgzip.LoginSignIn;

import java.sql.*;

/**
 @Author:   肖尧
 @Date: 2019.12.12

 数据库操作继承类，用于数据库各项操作。
 */

public class DataBaseController {

    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://120.78.208.4/javaProgrem";//待定调整
    String user =  "javaP" ;//待定调整
    String password  = "1801090042"; //待定调整
    String driver = "com.mysql.cj.jdbc.Driver";


    //关闭资源
    public void close(){

        try{

            if(rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ct != null) {
                ct.close();

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void jdbcConnection(){
        try {

            Class.forName(driver).newInstance();
            ct = DriverManager.getConnection(url,user,password);
            System.out.println("连接成功");
        }catch (Exception ex){
            System.out.println("连接失败");
            ex.printStackTrace();

        }

    }

    
    //执行某个查询方法
    public ResultSet queryExcecute(String sql){

        try {

            Class.forName(driver);

            ct = DriverManager.getConnection(url,user,password);

            ps = ct.prepareStatement(sql);

            rs = ps.executeQuery();

        }catch (Exception ex){
            ex.printStackTrace();

        }

        return rs;
    }


    public void queryUpdate(String sql){
        try {

            Class.forName(driver);

            ct = DriverManager.getConnection(url,user,password);

            ps = ct.prepareStatement(sql);

            ps.executeUpdate();

        }catch (SQLIntegrityConstraintViolationException e){
            // 数据库记录去重，该异常可以不处理
        } catch (Exception ex){
            ex.printStackTrace();

        }

    }


}
