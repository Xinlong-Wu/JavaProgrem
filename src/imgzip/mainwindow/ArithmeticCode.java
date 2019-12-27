package imgzip.mainwindow;

import java.util.ArrayList;
import java.util.Scanner;

public class ArithmeticCode {
    Scanner sc = new Scanner(System.in);
    public UnitCode[] arrU = new UnitCode[25];// 用以存储符号概率对象
    public int step = 1;


// 5=74.4703    01100
// 1=74.4643    01110
// 2=74.4606    00000
// #=64.7479    00010



    public ArithmeticCode() {
        int i = 0;
        arrU[i++] = new UnitCode("~", 0, 0.6304);
        arrU[i++] = new UnitCode("j", 0.6304, 11.4217);
        arrU[i++] = new UnitCode("n", 11.4217, 22.213);
        arrU[i++] = new UnitCode("b", 22.213, 33.0043);
        arrU[i++] = new UnitCode("Z", 33.0043, 65.3782);
        arrU[i++] = new UnitCode("_", 65.3782, 97.7521);
        arrU[i++] = new UnitCode(".", 97.7521, 103.126);
        arrU[i++] = new UnitCode("I", 103.126, 162.4999);
        arrU[i++] = new UnitCode("i", 162.4999, 194.8738);
        arrU[i++] = new UnitCode("P", 194.8738, 227.2477);
        arrU[i++] = new UnitCode("p", 227.2477, 259.6216);
        arrU[i++] = new UnitCode("0", 259.6216, 298.1109);
        arrU[i++] = new UnitCode("3", 298.1109, 340.1935);
        arrU[i++] = new UnitCode("8", 340.1935, 382.2763);
        arrU[i++] = new UnitCode("7", 382.2763, 424.3596);
        arrU[i++] = new UnitCode("4", 424.3596, 466.4436);
        arrU[i++] = new UnitCode("6", 466.4436, 508.5279);
        arrU[i++] = new UnitCode("9", 508.5279, 550.6161);
        arrU[i++] = new UnitCode("m", 550.6161, 593.7814);
        arrU[i++] = new UnitCode("g", 593.7814, 647.738);
        arrU[i++] = new UnitCode("/", 647.738, 712.4859);
        arrU[i++] = new UnitCode("#", 712.4859, 777.2338);
        arrU[i++] = new UnitCode("2", 777.2338, 851.6944);
        arrU[i++] = new UnitCode("1", 851.6944, 926.1587);
        arrU[i++] = new UnitCode("5", 926.1587, 1000);
    }

    // 编码过程
    public double Coder() {

        double gNum = 0; // 用于存储信息码的值
        // 左边界值和右边界值，以及左右边界值的差值
        double left = 0, right = 1, d = 1;
        UnitCode u1 = new UnitCode();

        while (true) {
            String flag = sc.next();
            int len = flag.length();
            for(int i = 0;i<len;i++){
                if (flag.equalsIgnoreCase("~")) {
                    return gNum;
                }
                Character c = flag.charAt(i);
                u1 = query(c.toString());
                left += d * u1.getlVal();
                right = left + d * u1.getP();
                gNum = left + (Math.random() * d * u1.getP());
                d = d * u1.getP();
                this.step++;
                /*
                 * System.out.getP()rintln("左值为"+left); System.out.getP()rintln("中值为"+d);
                 * System.out.getP()rintln("右值为"+right);
                 */
            }
        }
    }

    // 解码过程
    public String deCoder(double n) {
        int i = 0;
        double gNum = n;
        String str = "";
        double left = 0, right = 1, d = 1;
        UnitCode u1 = new UnitCode();

        while (true) {
            for (UnitCode u2 : arrU) {
                double ltmp = left + d * u2.getlVal();
                double rtmp = ltmp + d * u2.getP();
                if (ltmp <= gNum && gNum < rtmp) {
                    u1 = u2;
                    str += u1.getCode();
                }
            }
            left += d * u1.getlVal();
            right = left + d * u1.getP();
            d = d * u1.getP();
            if("00".equals(u1.getCode())){
                break;
            }
        }
        return str;
    }

    // 将数组中元素插入指定字符串
    public String join(String[] arr, String jStr) {
        String newStr = "";
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                newStr += arr[i] + jStr;
            } else {
                newStr += arr[i];
            }
        }
        return newStr;
    }

    // 查询输入符号
    public UnitCode query(String name) {

        for (int i = 24;i>=0;i--) {
            if (name.equals(arrU[i].getCode())) {
                return arrU[i];
            }
        }

        return null;
    }

    public static void main(String[] args) {

        ArithmeticCode ac = new ArithmeticCode();
        System.out.println("----请输入符号(如要退出请输入exit)----");
        double codeVal = ac.Coder();
        System.out.println("====输入结束====");
        System.out.println("\n生成信息码为:" + codeVal);
        System.out.println("\n----开始解码----");
        System.out.println(ac.deCoder(codeVal));
        System.out.println("----解码结束----");

    }

}

// 创建一个初始码类：UnitCode
class UnitCode {

    private String code;// 符号
    private double lVal;// 初始码概率的左边界值
    private double rVal;// 初始码概率的右边界值
    private double p;// 初始码概率值

    public UnitCode() {
    };

    public UnitCode(String code, double lVal, double rVal) {
        this.code = code;
        this.lVal = lVal;
        this.rVal = rVal;
        this.p = this.rVal - this.getlVal();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getlVal() {
        return lVal;
    }

    public void setlVal(double lVal) {
        this.lVal = lVal;
    }

    public double getrVal() {
        return rVal;
    }

    public void setrVal(double rVal) {
        this.rVal = rVal;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }
}
