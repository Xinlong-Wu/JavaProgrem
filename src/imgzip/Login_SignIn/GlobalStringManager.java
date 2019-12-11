package imgzip.Login_SignIn;

public class GlobalStringManager {
    static String account = "";

    public static void setAccount(String account) {
        GlobalStringManager.account = account;
    }

    public static String getAccount() {
        return account;
    }
}
