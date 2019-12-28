package imgzip.LoginSignIn;

import java.sql.*;

/**
 @Author:   肖尧
 @Date: 2019.12.28

 数据库操作继承类，用于数据库各项操作。
 */

public class DataBaseController {

    private PreparedStatement ps = null;
    private Connection ct = null;
    private ResultSet rs = null;
    private String url = "jdbc:mysql://120.78.208.4/javaProgrem";
    private String user =  "javaP" ;
    private String password  = "1801090042";
    private String driver = "com.mysql.cj.jdbc.Driver";


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

    //检查连接是否成功
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

    //执行某个查询语句
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

    //执行某个更新语句
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
