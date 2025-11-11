# ＝＝とequalsの違いは？
ちゃんと書いておかないと忘れそうだったので、記述

---
## <a  href="https://youtu.be/exesT6fqoik">【やさしくない!? Java】 ＝＝とequalsについての話4 OCJ-P Silver向け</a>
### ■まとめ  

一番おおもとにあるObjectにequalsっていうメソッドがある。  
しかしそいつは、自分でクラスを作る場合**オーバーライド**しないと使えない  
equalsをオーバーライドする際は必ずHash関数(?)もセットでオーバーライドする  

元からあるStringはややこしくて、  
```
■省略表記系は良い感じにやってくれるが、気を付けないといけない
String a = "あいう";
String b = "あいう";

a == b;         //true
a.equals(b);    //true

■わざわざnewを使えば、以下の様になる。
String a = new String("あいう");
String b = new String("あいう");

a == b;         //false
a.equals(b);    //true
```

### 省略形どうなってるの？
String a = "あいう";  
String b = "あいう";  
をコンパイルすると勝手に、Constant Poolってとこに割り当てられる。  
つまり、定数になるみたいなイメージ。 
 
だから同じ所を参照してるから
a == b は　true　ね！  
ってなる。

ちなみにIntegerとかは、128以上はpoolに入らないだの言っていたので、  
頭の隅に置いておきたい。


### 気になったので実際に動かしてみた

コメントの様になった。
```
    public static void main(String[] args) {

        //String a = new String("aaa");
        //String b = new String("aaa");     //false true　講義どおり

        // int a = 10;
        // int b = 10;      //equalsがエラー　←あ、そもそもintにイコールメソッドなんて無いのか。（え、でも、全てはObjectから派生してるから...あれ？（こまけぇこたあいいんだよ！

        // Integer a = 100;   
        // Integer b = 100;    //true true まぁまぁ。。。

        // Integer a = 1000;
        // Integer b = 1000;      //false true うわ、ほんとだｷｯｼｮ

        // double a = 1.1;
        // double b = 1.1;     //うん、equalsがエラー。多分プリミティブ型とかそこら辺の話が出てきそうだな...まぁ、でも使えないならないで大丈夫でしょう

        Double a = 1.1;
        Double b = 1.1;     //false true ありゃ、1.1で、もうダメなのね

        System.out.println(a == b);
        System.out.println(a.equals(b));

    }

```