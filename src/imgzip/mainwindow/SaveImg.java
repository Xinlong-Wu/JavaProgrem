package imgzip.mainwindow;

import javafx.scene.control.ComboBox;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

/**
 * @author 乌鑫龙
 *   保存文件的线程
 */
class SaveImg implements Runnable{
    String imgUrl;
    String[] newUrl;
    String storeName;
    ComboBox<String> trans;
    ImgBlock imgBlock;
    public SaveImg(ImgBlock imgBlock,String newUrl)throws IIOException {
        imgUrl = imgBlock.getUrl();
        if(! new File(imgUrl).exists()&&!imgUrl.startsWith("http:")){
            throw new IIOException("Imgae "+ imgUrl + " not exists");
        }
        this.newUrl = newUrl.split("\\.");
        this.imgBlock=imgBlock;
        this.trans = imgBlock.getTrans();
    }

    public SaveImg(ImgBlock imgBlock,String url ,String newUrl)throws IIOException {
        imgUrl = imgBlock.getUrl();
        if(! new File(imgUrl).exists()){
            throw new IIOException("Imgae "+ imgUrl + " not exists");
        }

        this.newUrl = newUrl.split("\\.");
        this.imgBlock=imgBlock;
        this.trans = imgBlock.getTrans();
    }
    @Override
    public void run() {
        File f2;
        if(imgUrl.startsWith("http:")){
            f2 = imgBlock.getNetUrlHttp(imgUrl);
        }else {
            //通过split截取文件路径
            String[] url=imgUrl.split("\\.");
            System.out.println(url[0]);
            f2 =new File(imgUrl);
        }

        //使用imgeIO来读取图片
        BufferedImage srcImg = null;
        try {
            srcImg = ImageIO.read(f2);
            if (trans.getValue().equals(ImgBlock.JPG)) {
                //重新创建图片
                BufferedImage newBufferedImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(srcImg, 0, 0, java.awt.Color.WHITE, null);
                ImageIO.write(newBufferedImage, "jpg", new File(newUrl[0] + ".jpg"));
                System.out.println(newUrl[0] + ".jpg");
            } else if (trans.getValue().equals(ImgBlock.PNG)) {
                //重新创建图片
                BufferedImage newBufferedImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(srcImg, 0, 0, java.awt.Color.WHITE, null);
                ImageIO.write(newBufferedImage, "png", new File(newUrl[0] + ".png"));
            } else if (trans.getValue().equals(ImgBlock.BMP)) {
                //重新创建图片(使用了awt包）
                int h = srcImg.getHeight(), w = srcImg.getWidth();
                int[] pixel = new int[w * h];
                PixelGrabber pixelGrabber = new PixelGrabber(srcImg, 0, 0, w, h, pixel, 0, w);
                pixelGrabber.grabPixels();
                MemoryImageSource m = new MemoryImageSource(w, h, pixel, 0, w);
                java.awt.Image image = Toolkit.getDefaultToolkit().createImage(m);
                BufferedImage newBufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_USHORT_565_RGB);
                newBufferedImage.createGraphics().drawImage(image, 0, 0, null);
                ImageIO.write(newBufferedImage, "bmp", new File(newUrl[0] + ".bmp"));
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        imgBlock.getIvstate().setImage(ImgBlock.DONE);
    }

}