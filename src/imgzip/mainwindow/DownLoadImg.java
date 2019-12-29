package imgzip.mainwindow;

import imgzip.alertwindow.AlertWindow;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 乌鑫龙
 */
public class DownLoadImg extends Task<Void> {
    Lock lock = new ReentrantLock();
    ImgBlock imgBlock;
    ImageView imageView;
    String url;
    static Object Lock = new Object();

    DownLoadImg(ImgBlock imgBlock,String url){
        this.imgBlock = imgBlock;
        this.imageView = imgBlock.getIvimg();
        this.url = url;
    }

    void downLoad(){
        String[] tmps = url.split("\\.");
        String type = tmps[tmps.length-1];

        //对本地文件命名
        String fileName = imgBlock.getIndex()+FunctionBox.crateUuid()+"."+type;
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            System.out.println("远程图片获取错误"+url);
            AlertWindow alertWindow = new AlertWindow("远程图片获取错误",url);
            alertWindow.start(new Stage());
            e.printStackTrace();
            file = null;
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imgBlock.setFile(file);
//        imgBlock.setTransBar(file.getPath());
        imgBlock.setImg(new Image(url));
    }

    @Override
    protected Void call() throws Exception {
        synchronized (Lock) {
            downLoad();
        }
        return null;
    }
}
