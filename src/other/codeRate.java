package other;

import java.util.*;


// 5=74.4703    01100
// 1=74.4643    01110
// 2=74.4606    00000
// #=64.7479    00010
// /=64.7479    00110
// g=53.9566    01010
// m=43.1653    00101
// 9=42.0882    00100
// 6=42.0843    01001
// 4=42.0840    01000
// 7=42.0833    011010
// 8=42.0828    011011
// 3=42.0826    011110
// 0=38.4893    011111
// p=32.3739    000010
// P=32.3739    000011
// i=32.3739    000110
// I=32.3739    000111
// .=32.3739    001110
// _=32.3739    001111
// Z=32.3739    010110
// b=10.7913    0101110
// n=10.7913    01011110
// j=10.7913    01011111
// ~=0.6304     1



/**
 * 计算协议码的码率以生成哈夫曼编码
 * @author 乌鑫龙
 */
public class codeRate {
    static int charCount = 0;
    static Map<String,Integer> counter = new HashMap<String,Integer>() ;
    static Map<String,Double> rste = new HashMap<String,Double>();
    public static void main(String[] args) {
        ArrayList<String> str = new ArrayList<>();
        String s = "";
        String[] type = {"jpg","png","bmp"};
        for (int i = 0;i < 10000000 ;i++){
            s ="512/#"+String.format("%04d",i)+"_imgZIP."+type[i%3]+"/#"+String.format("%06d",(int)(Math.random()*1000000));
            System.out.println(s);
            count(s);
        }
        Set<String> keys =  counter.keySet();
        for(String key : keys){
            rste.put(key, (double) (counter.get(key))/charCount);
        }
        System.out.println(rste);
    }

    static void count(String s){
        int len = s.length();
        charCount+=len;
        for (int i = 0;i < len;i++){
            Character c = s.charAt(i);
            Integer t = counter.get(c.toString());
            if(t!=null){
                counter.put(c.toString(),t+1);
            }else {
                counter.put(c.toString(),1);
            }
        }
    }
}
