package other;

import java.util.*;

// b=10.79132
// #=64.7479
// g=53.9566
// i=32.3739
// I=32.3739
// j=10.7913
// m=43.1653
// .=32.3739
// n=10.7913
// /=64.7479
// 0=38.4893
// P=32.3739
// p=32.3739
// 1=74.4643
// 2=74.4606
// 3=42.0826
// 4=42.0840
// 5=74.4703
// 6=42.0843
// 7=42.0833
// 8=42.0828
// 9=42.0882
// Z=32.3739
//_=32.37398


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
