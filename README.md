# [JavaProgram] 图像转化压缩及文件临时存储
>作者：乌鑫龙 吴泳仪 肖尧
---
## 目前想法：
  1. 图片的几种主流格式互相转化 
  2. 利用网上的图像压缩算法将图像进行处理
  
## 需求：

 - 界面   
 [ ] 一个登陆界面   
 [ ] 主界面，头部导航条有功能，用户名信息、    
 [ ] 中部主窗口将导入的图片显示   
 [ ] 用户界面     

 - 功能：   
 [ ] 利用后台服务器进行注册登陆，服务按月购买制  
 [x] 图片以类的形式展现  
 [ ] 注册功能：用户名，密码，邮箱，电话，头像  
 [ ] 找回密码  
 [x] jpg/png/bmp/tif 格式的图片互相转化（多线程技术）  
 [ ] 截图功能
 
 ## 遇到的问题
 1. 属性绑定，布局会乱
 
 ## 代码规范：
 > 采用[《阿里巴巴Java开发手册》](https://github.com/alibaba/p3c/blob/master/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%EF%BC%88%E5%8D%8E%E5%B1%B1%E7%89%88%EF%BC%89.pdf)

## 参考资料：
> [JavaFx Effect 之 Dropshadow 介绍使用](https://blog.csdn.net/qq_22571159/article/details/86570727)

>[JavaFX之Drag And Drop拖放操作](https://blog.csdn.net/wingfourever/article/details/8858782)


## 接口参数
 
 - (Scene) MainBox:
    - \- _SAVE_:Image 按钮图标    
    - \- _ADD_:Image 按钮图标   
    - \- _CLEAR_:Image 按钮图标   
    - \- _SAVED_:Image 按钮图标-深色   
    - \- _ADDD_:Image 按钮图标-深色   
    - \- _CLEARD_:Image 按钮图标-深色   
    - \- _imgCount_: int 用来记录imgblock的个数，方便删除
    - \- _blockList_: FlowPane 用来储存imglock   
    - \- _homePane_: BorderPane 主pane，因为要传给super所以必须为静态类   
    - \- imgList: HashSet 用来防止图片重复插入   
    - \- centerPane: ScollarPane 实现滚动显示   
    - \- tipLabelDark: ImageView 实现拖动提示   
    - \- ivSave: ImageView 按钮图标  
    - \- ivAdd: ImageView 按钮图标  
    - \- ivClear: ImageView 按钮图标 
   - MainBox()  **构造方法，调用生成主界面窗口Scene**    
   - \- setImg(ImageView iv, Image im):void **更改类中imgview的显示图片**   
   - \- addToBlockList(String path): void **添加一个block窗格类到BlockList**    
   - _drop_(ImgBlock block): void **静态方法删除图片(block窗格类)**    
   - \- _dropAll_(): void **删除主窗口所有的block窗格**    
   
## API
   1. 警告弹窗AlertWindow类，添加按钮的实例如下    
   ```java
    class Demo{
        Demo(){
            AlertWindow alertWindow = new AlertWindow("登陆失败","你尚未登录");  // 此处写警告弹窗的标题和内容
            // 用于新加按钮，可以新加多个，最后添加在VBox中
            alertWindow.anotherButton(vBox -> {
                  AlertButton alertButton = new AlertButton("asdasda");        //写新加按钮的名字
                  vBox.getChildren().add(alertButton);
                  alertWindow.getBtBoxChildren().add(vBox); // 最后一定要将VBox添加进alertWindow的btbox中
            });
            alertWindow.start(new Stage());
        }
    }
   ``` 

## 目前功能
1. 运用多线程，jpg/png/bmp/tif图片互相转化
2. 拖动加载图片f-