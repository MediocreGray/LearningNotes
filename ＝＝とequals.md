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