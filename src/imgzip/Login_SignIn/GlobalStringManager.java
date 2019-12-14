package imgzip.Login_SignIn;

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
