package imgzip.LoginSignIn;

/**
 @Author:   肖尧
 @Date: 2019.12.12

 全局变量类，用于项目内页面之间的信息传递。
 */

public class GlobalStringManager {
    static String account = "";
    static String emial = "";
    static String pic = "";

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

}
