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

4. 登录页面
     - 验证账号密码是否匹配
     - 账号密码栏是否为空
     - 记住账号密码
     - 进入注册账号、修改密码页面

5. 注册页面
    - 检查账号是否符合格式、是否为空、显示（取消显示）文字提醒用户(若不符合则无法点击注册按钮)
    - 检查密码是否符合长度要求、是否为空、检查密码强度、显示（取消显示）文字提醒用户(若不符合则无法点击注册按钮)
    - 检查邮箱是否为空、检查邮箱格式、显示（取消显示）文字提醒用户(若不符合则无法点击注册按钮)
    - 检查是否点击了“已读服务条款”的选择框(若不符合则无法点击注册按钮)
    - 打开系统默认网页，并登陆“服务条款”、隐私协议的外部网站，
    - 返回注册页面、个人页面按钮

6. 注册成功页面
    - 返回注册页面或再次注册页面的按钮

7. 验证身份页面
    - 检查账号、邮箱是否为空(若不符合则无法点击验证按钮)
    - 检查账号、邮箱是否唯一匹配对应(若不符合则无法点击验证按钮)
    - 提交验证数据按钮

8. 修改密码页面
    - 检查两次输入密码是否相同，是否为空(若不符合则无法点击验证按钮)
        - 检查密码是否符合长度要求、是否为空、检查密码强度、显示（取消显示）文字提醒用户(若不符合则无法点击修改密码按钮)
    - 提交修改数据

9. 修改密码成功页面
    - 返回个人信息页面（或登录页面）按钮
  
 ## 代码规范：
 > 采用[《阿里巴巴Java开发手册》](https://github.com/alibaba/p3c/blob/master/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%EF%BC%88%E5%8D%8E%E5%B1%B1%E7%89%88%EF%BC%89.pdf)

## 参考资料：
> [JavaFx Effect 之 Dropshadow 介绍使用](https://blog.csdn.net/qq_22571159/article/details/86570727)

>[JavaFX之Drag And Drop拖放操作](https://blog.csdn.net/wingfourever/article/details/8858782)

>[JavaFX文本框、按钮、列表框事件监听处理](https://blog.csdn.net/haoranhaoshi/article/details/82977050)

>[java正则表达式大全（常用）](https://blog.csdn.net/zpz2411232428/article/details/83549502)

>[加密解密（四）--Java中的Hash算法](https://blog.csdn.net/qq_24280381/article/details/72024860)


   
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
