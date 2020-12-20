
public class Practice {
    public static void main(String[] args) {

        //String a = new String("aaa");
        //String b = new String("aaa");     //false true　講義どおり

        // int a = 10;
        // int b = 10;      //equalsがエラー　←あ、そもそもintにイコールメソッドなんて無いのか。（え、でも、全てはObjectから派生してるから...あれ？（こまけぇこたあいいんだよ！

        // Integer a = 100;   
        // Integer b = 100;    //true true まぁまぁ。。。

         Integer a = 128;
         Integer b = 128;      //false true うわ、ほんとだｷｯｼｮ

        // double a = 1.1;
        // double b = 1.1;     //うん、equalsがエラー。多分プリミティブ型とかそこら辺の話が出てきそうだな...まぁ、でも使えないならないで大丈夫でしょう

        //Double a = 1.1;
        //Double b = 1.1;     //false true ありゃ、1.1で、もうダメなのね

        System.out.println(a == b);
        System.out.println(a.equals(b));

    }

}
