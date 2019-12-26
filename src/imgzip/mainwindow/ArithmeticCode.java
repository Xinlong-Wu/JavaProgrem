package imgzip.mainwindow;

import java.util.ArrayList;
import java.util.Scanner;

public class ArithmeticCode {
    Scanner sc = new Scanner(System.in);
    public UnitCode[] arrU = new UnitCode[4];// 用以存储符号概率对象
    public int step = 1;

    public ArithmeticCode() {
        arrU[1] = new UnitCode("00", 0, 0.1);
        arrU[0] = new UnitCode("01", 0.1, 0.5);
        arrU[2] = new UnitCode("10", 0.5, 0.7);
        arrU[3] = new UnitCode("11", 0.7, 1);

    }

    // 编码过程
    public double Coder() {

        double gNum = 0; // 用于存储信息码的值
        // 左边界值和右边界值，以及左右边界值的差值
        double left = 0, right = 1, d = 1;
        UnitCode u1 = new UnitCode();

        while (true) {
            String flag = sc.next();
            if (flag.equalsIgnoreCase("exit")) {
                return gNum;
            }
            u1 = query(flag);
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

        for (UnitCode UnitCode : arrU) {
            if (name.equals(UnitCode.getCode())) {
                return UnitCode;
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
