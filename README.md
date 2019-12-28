# [JavaProgram] 图像转化压缩及文件云端存储
>作者：乌鑫龙 吴泳仪 肖尧
---
  
## 目前功能

1. 主功能界面（
    - 拖动加载图片及拖动时UI的相应和文件过滤器
    - 去重添加图片
    - 单个图片的保存，删除，上传及其对应的批量操作
    - 图片操作时的UI响应
    - 添加图片快捷键（Ctrl+O）
    - 图片提取码的防注入功能
    - 图片下载功能
    - 图片多线程上传
    - 图片多线程保存
    - 图片多线程格式转换
2. 警告窗口（AlertWindow，AlertButton）
    - 三种不同的模式：自定义警告文本模式，定时自动关闭模式，跳转项目网站模式
    - 复制图片提取码到粘贴板
3. 后端服务器
    - 接收并定期处理过期图片
  
 ## 代码规范：
 > 采用[《阿里巴巴Java开发手册》](https://github.com/alibaba/p3c/blob/master/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%EF%BC%88%E5%8D%8E%E5%B1%B1%E7%89%88%EF%BC%89.pdf)

## 参考资料：
> [JavaFx Effect 之 Dropshadow 介绍使用](https://blog.csdn.net/qq_22571159/article/details/86570727)

>[JavaFX之Drag And Drop拖放操作](https://blog.csdn.net/wingfourever/article/details/8858782)
   
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

