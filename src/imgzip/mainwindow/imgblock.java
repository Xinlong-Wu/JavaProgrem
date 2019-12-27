package imgzip.mainwindow;



import imgzip.alertwindow.AlertWindow;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




/**
 * imgblock class
 *      imgblock主体框架
 * @author 乌鑫龙
 * @date 2019/11/12
 */
class ImgBlock extends BorderPane {
    /**
     * 静态参数加载各种资源图像
     * 静态变量：
     *      1. 静态图像资源：
     *          WAITING block左上角的等待图片（黄点）
     *          LOADING block左上角的队列中图片（会转的小彩虹）
     *          DONE block左上角的完成图片（绿点）
     *          DOWNLOAD block左上部的保存按钮图片
     *          UPLOAD block左上部的上传按钮图片
     *          CLOSE block左上部的上传按钮图片
     *      2. 静态变量：
     *          JPG 左下部下拉菜单的字符串
     *          PNG 左下部下拉菜单的字符串
     *          BMP 左下部下拉菜单的字符串
     *          saverPool 用于多线程保存图片的线程池
     *          uploadPool 用于多线程上传图片的线程池
     */
    static Image WAITING = new Image("res/icon/waiting.png");
    static Image LOADING = new Image("res/icon/loading.gif");
    static Image DONE = new Image("res/icon/done.png");
    private static Image DOWNLOAD = new Image("res/icon/download.png");
    private static Image UPLOAD = new Image("res/icon/upload.png");
    private static Image CLOSE = new Image("res/icon/close.png");
    static String JPG = "To JPG";
    static String PNG = "To PNG";
    static String BMP = "To BMP";
    private static ExecutorService saverPool = Executors.newCachedThreadPool();
    private static ExecutorService uploadPool = Executors.newCachedThreadPool();


    /**
     *  私有参数用于各个类特定的值、和对象，用来组成
     *
     *  ivimg block中心放置图片的类
     *  ivstate block左上角显示当前状态的类
     *  ivDwonLoad block左上部的下载按钮图标
     *  ivClose block右上部的关闭按钮图标
     *  ivUpload block左上部的上传按钮图标
     *  btClose block右上部的关闭按钮
     *  cent 用来显示边框线，美化界面
     *  trans 转换目标格式的下拉菜单
     *  url 图片的URL路径
     *  index Imgblock的编号
     *  isUpload 标记是否已经上传，来避免重复上传
     *  file block的图片文件
     *  isAcceed 判断该block是否成功创建，主要针对于提取码提取的图片，如果不成功，则不将imgBlock加入BlockList
     */
    private ImageView ivimg = new ImageView();
    private ImageView ivstate = new ImageView(WAITING);
    private ImageView ivDwonLoad = new ImageView(DOWNLOAD);
    private ImageView ivClose = new ImageView(CLOSE);
    private ImageView ivUpload = new ImageView(UPLOAD);
    private Button btClose = new Button();
    private StackPane cent = new StackPane();
    private ComboBox<String> trans = new ComboBox<>();
    private String url;
    private int index;
    private Boolean isUpload = false;
    private File file;
    private Boolean isAcceed = true;


//    Menu
    /**
     *
     * 图片预览框
     *
     */
    public ImgBlock(int imgCount,String imgUrl){
        //父属性及子属性设定
        this.setPadding(new Insets(10));
        HBox topBar = new HBox();
        this.setTop(topBar);
        VBox botBar = new VBox();
        this.setBottom(botBar);
        this.getStyleClass().add("block-bg");
        this.setCenter(cent);
        this.index = imgCount;
        this.url = imgUrl;
        //判断Url是否来自网络
        if (imgUrl.startsWith("http:")){
            file=getNetUrlHttp(imgUrl);
            if (file == null){
                setAcceed(false);
                return;
            }
            this.isUpload = true;
        } else {
            file= new File(imgUrl);
        }

        // 边框阴影设置
        DropShadow dropShadow =new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        dropShadow.setSpread(0.0);
        this.setEffect(dropShadow);


        //私有属性设定
        Image tmp ;
        if(isUpload){
            tmp = new Image(imgUrl);
        } else {
            tmp = new Image("file:"+imgUrl);
        }
        ivimg.setImage(tmp);
        ivimg.setPreserveRatio(true);
        ivimg.setPreserveRatio(true);
        if(tmp.getHeight()>=tmp.getWidth()*2/3){
            ivimg.setFitHeight(205);
        }else{
            ivimg.setFitWidth(295);
        }
        ivstate.setFitHeight(15);
        ivstate.setFitWidth(15);
        ivDwonLoad.setFitHeight(15);
        ivDwonLoad.setFitWidth(15);
        ivClose.setFitHeight(20);
        ivClose.setFitWidth(20);
        ivUpload.setFitHeight(15);
        ivUpload.setFitWidth(15);

        //关闭按钮设定
        btClose.setGraphic(ivClose);
        btClose.getStyleClass().add("block-box-close");
        HBox btBox = new HBox();
        btBox.getStyleClass().add("block-box");
        btBox.getChildren().add(btClose);

        // 顶部栏属性设定
        topBar.getStyleClass().setAll("block-topbar");
        topBar.setPadding(new Insets(0,0,0,10));

        //中间图片位置
        cent.getStyleClass().addAll("block-imgbox");
        cent.getChildren().addAll(ivimg);

        //底部栏属性设定
        HBox transBar = new HBox();
        botBar.getChildren().addAll(transBar);
        botBar.getStyleClass().setAll("block-botbar");

        //保存菜单设定
        MenuButton downLoad = new MenuButton();
        downLoad.setPadding(new Insets(0,0,0,10));
        MenuItem save = new MenuItem("保存");
        MenuItem saveAs = new MenuItem("另存为");
        save.getStyleClass().addAll("block-menu-basic");
        saveAs.getStyleClass().addAll("block-menu-basic");
        downLoad.getItems().add(save);
        downLoad.getItems().add(saveAs);
        downLoad.setGraphic(ivDwonLoad);
        downLoad.getStyleClass().addAll("block-menu-bt");

        //上传按钮
        Button btUpload = new Button();
        btUpload.setPadding(new Insets(0,0,0,10));
        btUpload.setGraphic(ivUpload);
        btUpload.getStyleClass().addAll("block-menu-bt");

        // 状态及关闭图标设定
        topBar.getChildren().add(ivstate);
        topBar.getChildren().add(downLoad);
        topBar.getChildren().add(btUpload);
        topBar.getChildren().add(btBox);

        //底部栏设定
        trans.setPadding(new Insets(0,0,0,10));
        trans.getItems().addAll(JPG,PNG,BMP);
        trans.getStyleClass().addAll("block-combo");
        transBar.getChildren().addAll(trans);
        transBar.getStyleClass().addAll("transbar");
        String type = file.getPath().split("\\.")[1].toLowerCase();
        switch (type) {
            case "png":
                trans.setValue(PNG);
                break;
            case "bmp":
                trans.setValue(BMP);
                break;
            default:
                trans.setValue(JPG);
                break;
        }//switch

        // 事件响应部分
        trans.setOnAction(e->{
            ivstate.setImage(WAITING);
            //销毁实例
            e.consume();
        });

        // 保存
        save.setOnAction(e->{
            ivstate.setImage(LOADING);
            try {
                saveImg(file.getPath());
            }
            catch (IIOException ex){
                System.out.println("Imgblock->save->action: "+ex.getMessage());
            }
            finally {
                //销毁实例
                e.consume();
            }
        });

        // 另存为
        saveAs.setOnAction(e->{
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择保存路径");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("全部图片（.bmp/.png/.jpg)", "*.bmp","*.jpg","*.png"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"));
            try{
                String path = fileChooser.showSaveDialog(stage).getPath();
                System.out.println(path);
                ivstate.setImage(LOADING);
                saveImg(path);
            }
            catch (IIOException ex){
                System.out.println("Imgblock->save->action: "+ex.getMessage());
            }
            catch (NullPointerException ex){
                System.out.println("imgBlock->saveAs->action->fileChooser: "+ex);
            }
            finally {
                //销毁实例
                e.consume();
            }
        });

        // 上传
        btUpload.setOnAction(e->{
            //检查登录！！！！
            uploadImg();
            //销毁实例
            e.consume();
        });

        //关闭按钮
        btClose.setOnAction(e->{
            FunctionBox.drop(this);
            //销毁实例
            e.consume();
        });


    }

    void setState(Image state){
        ivstate.setImage(state);
    }

    ImageView getIvstate() {
        return ivstate;
    }

    ComboBox<String> getTrans() {
        return trans;
    }


    void setUpload() {
        isUpload = true;
    }

    /**
     * 保存图片方法
     * @param path
     */
    void saveImg(String path) throws IIOException{
            saverPool.execute(new SaveImg(this,path));
    }

    /**
     * 图片上传方法
     */
    void uploadImg(){
        ivstate.setImage(LOADING);
        UploadImg uploadImg;
        if(isUpload){
            AlertWindow alertWindow = new AlertWindow("图片已经存在","图片已经存在于云端");
            alertWindow.start(new Stage());
            ivstate.setImage(WAITING);
            return;
        }
        try {
            uploadImg = new UploadImg(this);
            uploadPool.execute(uploadImg);
        } catch (IIOException e) {
            ivstate.setImage(WAITING);
            return;
        }

        FunctionBox.upLoading(uploadImg.getUuid());
    }//uploadImg

    void uploadImg(String uuid){
        ivstate.setImage(LOADING);
        if(isUpload){
            ivstate.setImage(DONE);
            return;
        }
        try {
            uploadPool.execute(new UploadImg(this,uuid));
        } catch (IIOException e) {
            ivstate.setImage(WAITING);
        }
    }//uploadImg

    /**
     *  图片url访问器
     * @return
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * block 创建是否成功访问器和修改器
     * @return
     */
    public Boolean getAcceed() {
        return isAcceed;
    }

    public void setAcceed(Boolean acceed) {
        isAcceed = acceed;
    }

    /**
     * 图像序号访问器
     * @return
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * 图像文件访问器
     * @return
     */
    public File getFile() {
        return file;
    }


    public File getNetUrlHttp(String netUrl) {
        String[] tmps = netUrl.split("\\.");
        String type = tmps[tmps.length-1];

        //对本地文件命名
        String fileName = getIndex()+FunctionBox.crateUuid()+"."+type;
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(netUrl);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            System.out.println("远程图片获取错误"+netUrl);
            AlertWindow alertWindow = new AlertWindow("远程图片获取错误",netUrl);
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
        return file;
    }//getNetUrlHttp
}//class ImgBlock