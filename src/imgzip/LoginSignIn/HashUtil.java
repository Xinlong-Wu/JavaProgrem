package imgzip.LoginSignIn;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtil {

    /**
     * 计算字符串的hash值
     * param string    密码的明文值
     * param algorithm 算法名
     * return 字符串的hash值
     */
    public static String hash(String string) {
        if (string.isEmpty()) {
            return "";
        }

        /**
         * MessageDigest 用于生成明文密码的摘要值，
         * 明文密码相同，生成的摘要值相同。
         * 摘要值的计算方法固定使用SHA1方法。长度为160位
         */
        String algorithm = "SHA1";
        MessageDigest hash = null;
        try {

            /**
             *hash储存了SHA1方法生成的明文密码的摘要后
             * ①使用digest方法，对原明码字符串通过UTF-8解码方法转为指定的字符序列后的字节数组进行最后的更新，此时已经生成了摘要。
             */
            hash = MessageDigest.getInstance(algorithm);
            byte[] bytes = hash.digest(string.getBytes("UTF-8"));
            String result = "";

            /**
             * ①对摘要的每一位字符，由于java储存字符是存其补码，由于byte是8位，int是32位，int转换会在其前添加24位0的补码，也就是24位1作为转换的补位。但仅仅保持十进制一致
             * ②但是转为int值的时候，二进制补码也变化了，为了使其二进制补码不受改变，也就是其二进制的值是要求不变。 0000000 100001 和 100001 二进制是一致的，但是在十进制中不一致。
             * 因此需要把前面填充的11111111.....变为00000000000000
             * ③toHexString 方法：
             * 1：根据传入整数的有效二进制长度，得到最后随机生成的字符输出的长度（范围是1-4）
             * 2：根据传入的整数值和整数值的二进制长度，不断取余得到下标值（还不是很懂），再根据得到的随机下标值获得java内置字符数组中的字符，组成字符串。
             *
             * ③：如果toHexString方法：得到了一个长度为1的字符串，那么就在这个字符串前加一个字符“0”
             * ④: 最终将每一个字符产生的字符串，加到结果字符串中并返回。结果即为加密后的字符串。
             */
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}


/**
 * toHexString 内置的唯一方法，方便理解。
 */
//private static String toUnsignedString0(int val, int shift) {
//
//    //这里所有的未注明的shift都是4，即16进制
//
//    // assert shift > 0 && shift <=5 : "Illegal shift value";
//    //求得val 的二进制形式的 有效位数  例如：val=15 就是 mag=4位
//    int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
//
//    //计算的 目标(shift)进制形式字符串的值 的位数。
//    //例如：val=15 求二进制字符串就是chars=4,8进制chars就是2,16进制chars就是1
//    //(((mag + (shift - 1)) / shift), 1)这里是为了保证，足够的个数，就像val=17,那么mag=5，chars该为2，用mag直接除shift的话chars是1，就不对了
//    int chars = Math.max(((mag + (shift - 1)) / shift), 1);
//    char[] buf = new char[chars];
//
//    formatUnsignedInt(val, shift, buf, 0, chars);
//
//    // Use special constructor which takes over "buf".
//    //这里利用java String的带有数组的构造方法，构造出目标字符串。
//    return new String(buf, true);
//}


/**
 * toUnsignedString 内置的唯一方法，方便理解。
 */
//    static int formatUnsignedInt(int val, int shift, char[] buf, int offset, int len) {
//        int charPos = len;
//        int radix = 1 << shift;
//        int mask = radix - 1;
//        do {
//            buf[offset + --charPos] = Integer.digits[val & mask];//每次do-while循环都会取4位（从高位到低位），Integer.digits[]数组是十六进制的字符集
//            val >>>= shift;
//        } while (val != 0 && charPos > 0);
//
//        return charPos;
//    }
//
//    final static char[] digits = {
//            '0' , '1' , '2' , '3' , '4' , '5' ,
//            '6' , '7' , '8' , '9' , 'a' , 'b' ,
//            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
//            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
//            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
//            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
//    };
