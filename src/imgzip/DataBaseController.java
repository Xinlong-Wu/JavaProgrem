package imgzip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
