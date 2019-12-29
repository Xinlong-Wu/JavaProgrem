package backend;

import imgzip.LoginSignIn.DataBaseController;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileManger implements Runnable{
    static ExecutorService fileMangePool = Executors.newCachedThreadPool();
    private DataBaseController dbc;
    private String sql;
    static {
        Log4jPrintStream.redirectSystemOut();
    }
    FileManger(){
        dbc = new DataBaseController();
        sql = "SELECT * FROM `imgRecycle`";
    }

    @Override
    public void run() {
        ResultSet rs ;
        String[] urls;
        while(true){
            rs = dbc.queryExcecute(sql);
            urls = null;
            try {
                rs.last();
                int row = rs.getRow();
                urls = new String[row];
                rs.first();
                for(int count = 0;count<row;count++) {
                    urls[count] = rs.getString(1);
                    rs.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(urls!=null){
                for (String filename: urls) {
                    System.out.println("正在删除"+filename);
                    File file = new File("javaProImgs",filename);
                    if (file.isFile()){
                        file.delete();
                    }
                }
            }
            dbc.queryUpdate("DELETE FROM `imgRecycle`");
            dbc.close();
            System.out.println("Manager成功运行");
            //线程阻塞1天
            try {
//            Thread.sleep(1000*3600*24);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
