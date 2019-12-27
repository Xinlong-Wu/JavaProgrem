package imgzip.mainwindow;

import imgzip.FunctionPane;
import imgzip.LoginSignIn.DataBaseController;
import imgzip.alertwindow.AlertButton;
import imgzip.alertwindow.AlertWindow;
import imgzip.mainpane.Course;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;


/**
 * @author 乌鑫龙
 * 抽象成Scene的目的是在关闭软件之前可以保留该页面的当前状态
 */
public class FunctionBox extends Scene {
    /**
     *  静态参数图像计数器和blockList，借此完成图片block的新建及删除
     *  静态变量：
     *      1. 静态图像资源：
     *          ADD 窗口控制栏的图添加按钮图标Imgae类
     *          SAVE 窗口控制栏的图像保存按钮图标Imgae类
     *          CLEAR 窗口控制栏的图像清空按钮图标Imgae类
     *          UPLOAD 窗口控制栏的图像上传按钮图标Imgae类
     *          BACK 窗口控制栏的图像返回按钮图标Imgae类
     *          与之对应的还有各自的深色图标，用来实现鼠标进入的响应效果
     *
     *      2. imgCount 用来计录当前窗口已添加图片的数量,当为0时，保存下载等部分按钮不可用，在这里当做图片的ID使用，会传入每一个imgBlock，
     *
     *      3. blockList 用一个FlowPane直接储存imgBlock类
     *
     *      4. homePane 用BorderPane实现之后的顶部功能栏,因为要在构造方法中传入父类构造方法，所以需要静态
     *
     *      5. imgList 一个String型的HashSet类，利用set类元素不重复的特点，通过其中保存图片路径来过滤已经添加过的图片
     *
     *  注：该类中的所有批量操作都是遍历调用BlockList中每一个类的相应方法实现的
     *
     */
    private static Image SAVE = new Image("res/icon/save.png");
    private static Image ADD = new Image("res/icon/add.png");
    private static Image CLEAR = new Image("res/icon/clear.png");
    private static Image UPLOAD = new Image("res/icon/upload&font.png");
    private static Image BACK = new Image("res/icon/back.png");
    private static Image SAVED = new Image("res/icon/save-dark.png");
    private static Image ADDD = new Image("res/icon/add-dark.png");
    private static Image CLEARD = new Image("res/icon/clear-dark.png");
    private static Image UPLOADD = new Image("res/icon/upload&font-dark.png");
    private static Image BACKD = new Image("res/icon/back-dark.png");
    private static int imgCount=0;
    private static FlowPane blockList = new FlowPane();
    private static BorderPane homePane = new BorderPane();
    private static HashSet<String> imgList = new HashSet<>();

    /**
     *   主界面 和 顶部栏
     *   私有变量：
     *         centerPane 为了实现拖动文件时有图层悬浮在最顶端，所以需要StackPane
     *         tipLabelDark 实现上述功能，显示的图层的文字，上述图层其实是设置了背景的Label标签
     *         ivSave 窗口控制栏的图像保存按钮图标相应的ImageView类
     *         ivAdd 窗口控制栏的图添加按钮图标相应的ImageView类
     *         ivClear 窗口控制栏的图像清空按钮图标相应的ImageView类
     *         ivUpload 窗口控制栏的图像上传按钮图标相应的ImageView类
     *         ivBack 窗口控制栏的图像返回按钮图标相应的ImageView类
     *         btClear 窗口控制栏的图像清空按钮
     *         btSave 窗口控制栏的图像保存按钮
     *         btUpload 窗口控制栏的图像上传按钮
     */
    private StackPane centerPane = new StackPane();
    private Label tipLabelDark = new Label("从本地文件夹拖动图片到这里");
    private ImageView ivSave = new ImageView(SAVE);
    private ImageView ivAdd = new ImageView(ADD);
    private ImageView ivClear = new ImageView(CLEAR);
    private ImageView ivUpload = new ImageView(UPLOAD);
    private ImageView ivBack = new ImageView(BACK);
    private static Button btClear = new Button();
    private static Button btSave = new Button();
    private static Button btUpload = new Button();

    /**
     * 将主界面整合成一个Scene类，方便调用
     *
     *  root   静态homePane，因为要在构造方法中传入父类构造方法，所以需要静态
     *  width  1024 FunctionBox宽度
     *  height 800 FunctionBox高度
     *
     */
    public FunctionBox() {
        //窗口尺寸和整个窗口所用到的CSS文件
        super(homePane, 1024, 800);
        this.getStylesheets().add("css/imgblock.css");

        //设置静态变量 blockList 的属性（CSS和背景。这里背景用的是“从本地文件夹拖动图片到这里”的图片）
        blockList.getStyleClass().add("main-box");
        blockList.setBackground(new Background(new BackgroundImage(new Image("res/icon/background.png",1024,700,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));


        /*
         *设置私有值和相应FX类的CSS属性
         *      centerScroll 用 ScrollPane 类实现拖入一定数量的ImgBlock可以进行滚动的效果，并且固定大小，
         *      centerPane 添加滚动窗口centerScroll和在拖动时会显示在顶端的的tipLabelDark
         *      将centerPane添加进homePane 的中部
         */
        ScrollPane centerScroll = new ScrollPane();
        centerScroll.setContent(blockList);
        centerScroll.getStyleClass().addAll("scroll-pane");
        centerScroll.setFitToWidth(true);
        centerScroll.setFitToHeight(true);
        centerPane.getChildren().addAll(centerScroll,tipLabelDark);
        centerPane.getStyleClass().add("center-pane");
        homePane.setCenter(centerPane);

        // 顶部组件
        // 菜单栏
        // 会有键盘快捷键 "打开  (Ctrk+O)"
        MenuItem about = new MenuItem("关于 imagine");
        MenuItem exit = new MenuItem("退出");
        Menu imagine = new Menu("imagine");
        imagine.getItems().addAll(about,exit);
        MenuItem open = new MenuItem("打开  (Ctrk+O)");
        Menu file = new Menu("文件");
        file.getItems().addAll(open);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(imagine,file);

        // 控制栏
        // 默认最初的图片都是浅色图片资源
        // 添加按钮
        Button btAdd = new Button();
        btAdd.setGraphic(ivAdd);
        btAdd.getStyleClass().addAll("top-ctrl-bt");

        // 上传按钮
        btUpload.setGraphic(ivUpload);
        btUpload.getStyleClass().addAll("top-ctrl-bt");

        // 保存按钮
        btSave.setGraphic(ivSave);
        btSave.getStyleClass().addAll("top-ctrl-bt");

        // 清空按钮
        btClear.setGraphic(ivClear);
        btClear.getStyleClass().addAll("top-ctrl-bt");

        // 返回按钮
        Button btBack = new Button();
        btBack.setGraphic(ivBack);
        btBack.getStyleClass().addAll("top-ctrl-bt");

        // 设置 返回 清空 保存三个按钮在一开始imgCount为0时处在不可用状态
        btClear.setDisable(true);
        btSave.setDisable(true);
        btUpload.setDisable(true);

        // 图片下载模块
        // 图片下载提取码输入框，后面会添加回车监听器，提升用户体验
        TextField tfImgIdInput = new TextField();
        tfImgIdInput.getStyleClass().addAll("btImgIdInput");
        tfImgIdInput.setPromptText("请输入图片提取码");
        // 图片下载按钮
        Button btLoad = new Button("GO!");
        btLoad.getStyleClass().addAll("top-ctrl-bt");
        HBox imgDownloadBox = new HBox(5);
        imgDownloadBox.getStyleClass().addAll("imgDownloadBox");

        // 将图片下载的两个部分整合在一起
        imgDownloadBox.getChildren().addAll(tfImgIdInput,btLoad);

        // 在将上述图片下载部分嵌套在新的HBox，便于实现居右显示
        // 因为居右显示的原理是一个特别长的HBox
        HBox toRightBox = new HBox(0);
        toRightBox.getStyleClass().addAll("toRightBox");
        toRightBox.getChildren().addAll(imgDownloadBox);
        toRightBox.setPadding(new Insets(0,10,0,0));

        // 将上述所有组件放入控制栏
        HBox ctrlBar = new HBox(20);
        ctrlBar.getChildren().addAll(btAdd,btSave,btClear,btUpload,btBack,toRightBox);
        ctrlBar.getStyleClass().addAll("top-ctrl");
        ctrlBar.setPadding(new Insets(0,0,0,20));

        // 将菜单栏和控制栏放入一纵向VBox，将VBox放入homePane的顶部区域
        VBox topPane = new VBox();
        topPane.getChildren().addAll(menuBar,ctrlBar);
        topPane.getStyleClass().addAll("top-pane");
        homePane.setTop(topPane);

        // 设置主界面的背景提示，用label实现，默认将该标签隐藏。
        tipLabelDark.getStyleClass().addAll("background-lable");
        tipLabelDark.setAlignment(Pos.BASELINE_CENTER);
        tipLabelDark.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.12),null,null)));
        tipLabelDark.setVisible(false);

        /**
         *
         *      页面布局结束
         *      事件相应部分开始
         *
         */

        //  拖动载入文件方法，提升用户体验
        centerPane.setOnDragEntered(e->{
            System.out.println("enter");
            tipLabelDark.setVisible(true);
        });
        centerPane.setOnDragExited(e->{
            System.out.println("exist");
            tipLabelDark.setVisible(false);
        });
        centerPane.setOnDragDropped(e->{
            System.out.println("dropped");
            Dragboard dragboard = e.getDragboard();
            List<File> imgs = dragboard.getFiles();
            imgs.forEach(img->{
                String path = img.getPath();
                addToBlockList(path);
                tipLabelDark.setVisible(false);
            });
            checkBlockList();
        });
        //  设置允许拖放文件（不写这个拖放总不成功）
        //  并且利用这个监听器对拖入的文件对文件进行过滤，如果后缀名为png/jpg/bmp才允许放入，否则一律不允许
        centerPane.setOnDragOver(e->{
            if (e.getGestureSource() != centerPane
                    && e.getDragboard().hasFiles()) {
                Dragboard dragboard = e.getDragboard();
                List<File> imgs = dragboard.getFiles();
                for (File img : imgs) {
                    String[] path = img.getPath().split("\\.");
                    String type = path[path.length - 1].toLowerCase();
                    if ("png".equals(type) || "jpg".equals(type) || "bmp".equals(type)) {
                        /* allow for both copying and moving, whatever user chooses */
                        e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    } else {
                        e.acceptTransferModes(TransferMode.NONE);
                        break;
                    }//if("png".equals(type) || "jpg".equals(type) || "bmp".equals(type))
                }//for(File img : imgs)
            }//if(e.getGestureSource() != centerPane&& e.getDragboard().hasFiles())
            e.consume();
        });//centerPane.setOnDragOver

        // 清空blockList
        // 设置鼠标移入效果响应
        btClear.setOnMouseEntered(e->{
            setImg(ivClear,CLEARD);
            e.consume();
        });
        // 设置鼠标移出效果响应
        btClear.setOnMouseExited(e->{
            setImg(ivClear,CLEAR);
            e.consume();
        });
        // 设置响应主体
        btClear.setOnAction(e->{
            dropAll();
            checkBlockList();
        });

        // 上传图片
        // 设置鼠标移入效果响应
        btUpload.setOnMouseEntered(e->{
            setImg(ivUpload,UPLOADD);
            e.consume();
        });
        // 设置鼠标移出效果响应
        btUpload.setOnMouseExited(e->{
            setImg(ivUpload,UPLOAD);
            e.consume();
        });
        // 设置响应主体
        btUpload.setOnAction(e->{
            // 用UUID作为提取码
            String uuid = crateUuid();
            //获取图片列表
            List imgBlockList = blockList.getChildren();
            for (int i = 0;i < imgCount;i++){
                ImgBlock tmp = (ImgBlock)imgBlockList.get(i);
                tmp.uploadImg(uuid);
            }
            upLoading(uuid);
        });

        // 从文件管理器打开图像
        // 设置鼠标移入效果响应
        btAdd.setOnMouseEntered(e->{
            setImg(ivAdd,ADDD);
            e.consume();
        });
        // 设置鼠标移出效果响应
        btAdd.setOnMouseExited(e->{
            setImg(ivAdd,ADD);
            e.consume();
        });
        // 设置响应主体
        btAdd.setOnAction(new OpenFiles());

        //保存图片
        // 设置鼠标移入效果响应
        btSave.setOnMouseEntered(e->{
            setImg(ivSave,SAVED);
            e.consume();
        });
        // 设置鼠标移出效果响应
        btSave.setOnMouseExited(e->{
            setImg(ivSave,SAVE);
            e.consume();
        });
        // 设置响应主体
        btSave.setOnAction(e->{
            Stage stage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择文件目录");
            try{
                String path = directoryChooser.showDialog(stage).getPath();
                System.out.println(path);
                ObservableList i = blockList.getChildren();
                // 遍历所有的ImgBlock
                for(int j = 0;j<i.size();j++){
                    ((ImgBlock)i.get(j)).setState(ImgBlock.LOADING);
                    ((ImgBlock)i.get(j)).saveImg(path+"\\"+"ImgZip_"+String.format("%04d",j));
                }//for
            }//try
            catch (IIOException ex){
                System.out.println("MainBos->btAdd->action->file: "+ex.getMessage());
            }
            catch (NullPointerException ex){
                System.out.println("MainBos->btAdd->action->list: "+ex);
            }
            checkBlockList();
        });

        //返回按钮
        // 设置鼠标移入效果响应
        btBack.setOnMouseEntered(e->{
            setImg(ivBack,BACKD);
            e.consume();
        });
        // 设置鼠标移出效果响应
        btBack.setOnMouseExited(e->{
            setImg(ivBack,BACK);
            e.consume();
        });
        // 设置响应主体
        btBack.setOnAction(e->{
            //唤起其他窗口
            new Course();
            FunctionPane.close();
        });

        // 图片提取
        // 设置按钮事件响应
        btLoad.setOnAction(e->{
            String id = tfImgIdInput.getText();
            if(!id.isEmpty()){
                loadImgs(id);
                tfImgIdInput.clear();
            }
        });
        // 设置提取码输入框的回车快捷键
        tfImgIdInput.setOnAction(e->{
            String id = tfImgIdInput.getText();
            if(!id.isEmpty()){
                loadImgs(id);
                tfImgIdInput.clear();
            }
        });

        // 键盘快捷键
        // "打开  (Ctrk+O)"
        homePane.setOnKeyPressed(e -> {
            if(e.isControlDown()&&e.getCode() == KeyCode.O){
                new OpenFiles().handle(null);
            }
        });

        // 菜单栏中打开按钮的事件响应
        open.setOnAction(new OpenFiles());

        // 菜单栏中的关于按钮事件响应
        // 此处用到了自己写的方法及接口
        about.setOnAction(event -> {
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.start(new Stage());
        });

        // 菜单栏中的关闭按钮的事件响应
        exit.setOnAction(event -> FunctionPane.close());
    }//FunctionBox()

    /**
     *  修改ImageView中的图片
     *  统一的静态方法用来由于 更改切换一切需要切换更改的图片
     * @param iv,im
     */
    public static void setImg(ImageView iv, Image im){
        iv.setImage(im);
    }//setImg

    /**
     *  添加block窗格到BlockList(禁止重复插入）
     *  用HashSet的add的返回值来判断是否重复
     * @param path
     */
    private void addToBlockList(String path){
        if(imgList.add(path)){
            ImgBlock tmp = new ImgBlock(++imgCount,path);
            if(tmp.getAcceed()){
                blockList.getChildren().add(tmp);
            }//if(tmp.getAcceed())
        }//if(imgList.add(path))
    }//addToBlockList

    /**
     *  静态方法删除图片
     * @param block
     */
    static void drop(ImgBlock block){
        String url = block.getUrl();
        if(imgCount>1){
            imgList.remove(url);
            blockList.getChildren().remove(block);
            imgCount--;
        }//if
        else {
            imgList.clear();
            blockList.getChildren().clear();
            imgCount--;
        }//else
        checkBlockList();
    }//drop

    /**
     * 删除全部图片
     */
    private void dropAll(){
        blockList.getChildren().clear();
        imgList.clear();
        imgCount=0;
        checkBlockList();
    }//dropAll()

    /**
     * 列表检查器
     * 检查imgCount是否为0，是则将保存 清除 上传按钮不可用
     */
    private static void checkBlockList(){
        if(imgCount==0){
            btClear.setDisable(true);
            btSave.setDisable(true);
            btUpload.setDisable(true);
        }
        else{
            btClear.setDisable(false);
            btSave.setDisable(false);
            btUpload.setDisable(false);
        }
    }//checkBlockList()

    /**
     * 图片批量上传方法
     * 循环调用每一个ImgBlock的上传方法
     * @param uuid
     */
    static void upLoading(String uuid){
        AlertWindow alertWindow = new AlertWindow("正在上传","您的图片提取码:\n"+uuid);
        // 此处写警告弹窗的标题和内容
        // 用于新加按钮，可以新加多个，最后添加在VBox中
        alertWindow.anotherButton(vBox -> {
            AlertButton alertButton = new AlertButton("拷贝");        //写新加按钮的名字
            vBox.getChildren().add(alertButton);
            alertWindow.getBtBoxChildren().add(vBox); // 最后一定要将VBox添加进alertWindow的btbox中
            alertButton.setOnAction(ee->{
                setclipboardtext(uuid);
                alertButton.setText("已复制到剪贴板上");
            });
        });
        alertWindow.start(new Stage());
    }//upLoading

    /**
     * 将图片ID添加到剪贴板
     * ImgBlock中也会进行调用，所以设为静态
     * @param note
     */
    private static void setclipboardtext(String note) {
        Clipboard clip = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(note);
        clip.setContent(clipboardContent);
    }//setclipboardtext

    private void loadImgs(String uuid){
        boolean t = Pattern.matches(".*[^a-z|0-9].*",uuid );
        if(t){
            AlertWindow alertWindow = new AlertWindow("提取码有误","您的提取码有误，请重新输入");
            alertWindow.start(new Stage());
            return;
        }
        String[] fileName = getImgsUrl(uuid);
        for (String s : fileName) {
            System.out.println("http://www.wulongxin.com/javaP/javaProImgs/"+s);
            addToBlockList("http://www.wulongxin.com/javaP/javaProImgs/"+s);
        }
        checkBlockList();
    }

    /**
     * 图片云端下载时通过提取码获取图片路径的方法，因为调用数据库，所以设置私有属性
     * @param uuid
     * @return 返回图片文件的云端URL
     */
    private String[] getImgsUrl(String uuid){
        DataBaseController dbc = new DataBaseController();
        String sql = "SELECT `imgUrl` AS `url` FROM `imgcount` where `groupUuid` = '"+uuid+"'";
        ResultSet rs = dbc.queryExcecute(sql);
        String[] urls = null;
        try {
            rs.last();
            int row = rs.getRow();
            urls = new String[row];
            rs.first();
            for(int count = 0;count<row;count++) {
                urls[count] = rs.getString(1);
                rs.next();
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertWindow alertWindow = new AlertWindow("数据库登陆失败",e.getMessage());
            alertWindow.start(new Stage());
        }
        dbc.close();
        return urls;
    }//getImgsUrl

    /**
     * 用于生成批量上传的提取码UUID
     * @return
     */
    static String crateUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }//crateUuid

    /**
     * 内部类
     * 用于打开文件事件处理器
     */
    class OpenFiles implements EventHandler{
        @Override
        public void handle(Event e) {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择图片");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("全部图片（.bmp/.png/.jpg）", "*.bmp","*.jpg","*.png"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"));
            try{
                List list = fileChooser.showOpenMultipleDialog(stage);
                list.forEach(li->{
                    String path = li.toString();
                    System.out.println(path);
                    addToBlockList(path);
                });
            }//try
            catch (Exception ex){
                System.out.println("MainBos->btAdd->action->list: "+ex);
            }//catch
            finally {
                checkBlockList();
            }//finally
        }//handle
    }//class OpenFiles

} //class FunctionBox
