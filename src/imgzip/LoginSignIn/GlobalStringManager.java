package imgzip.LoginSignIn;

import java.util.ArrayList;

/**
 @Author:   肖尧
 @Date: 2019.12.12

 全局变量类，用于项目内页面之间的信息传递。
 account:用户名
 emial : 邮箱
 pic ：头像
 picSequences : 图片序列，用于缓存新添加的序列。

 ①：全局变量类中，当登录页面时，自动载入对于账号的用户名、邮箱、图片序列串
 ②：用户名用于修改密码页面、个人信息页面的信息载入
 ③：图片序列串，用于实时更新用户上传的图片的提取码，不同的用户会有不同的提取码，每次回到个人信息页面，都会更新用户的所有当前的提取码。
 */

public class GlobalStringManager {
    static String account = "";
    static String emial = "";
    static String pic = "";
    static ArrayList<String> picSequences = new ArrayList<String>();

    public static void setAccount(String account) {
        GlobalStringManager.account = account;
    }

    public static String getAccount() {
        return account;
    }

    public static void setEmial(String emial) {
        GlobalStringManager.emial = emial;
    }

    public static void setPic(String pic) {
        GlobalStringManager.pic = pic;
    }

    public static String getEmial() {
        return emial;
    }

    public static String getPic() {
        return pic;
    }

    public static ArrayList<String> getPicSequences() {
        return picSequences;
    }

    public static void setPicSequences(String sequence) {
        picSequences.add(sequence);
    }




}
