# ラムダ式
StreamAPIを最初に学ぼうと思ったが、
どうやらラムダ式ありきのStreamAPIっぽいので
こっちから学んでいく

## 難しさ
Javaとか  
どこで使うかが分からんから難しいらしい  



## 参考になった資料(ラムダ式)
<a  href="https://youtu.be/Lhr9ZCmT1b0">【やさしくない!? Java】 ラムダ式の話１ OCJ-P Gold向け</a>←★これ見よう





# ジェネリクスが分かんなくなってきた

## 見た資料(ジェネリクス)

### <a  href="https://youtu.be/_VKCSaN-fqs">【やさしくない!? Java】ジェネリクスの話１ OCJ-P Gold向け</a>
↑何故ジェネリクスがあるのか  
#### ■まとめ  
『リストにジェネリクスが無いとなんでも入れれる！だからコンパイルでは気付けないバグがうんぬん！』  
#### ■一言  
「設計とかここら辺学んでると思うけど、長期的に見てバグを出させない様にめちゃくちゃ工夫してるなぁって思いました(小並感）」

----

### <a  href="https://youtu.be/tRhoymCkXs4">【やさしくない!? Java】ジェネリクスの話２ OCJ-P Gold向け</a>
↑実際に作ってみよう！
#### ■まとめ
まぁ、基本的なジェネリクスの使い方
#### ■一言
```
Sample<String> sample = new Sample<>   

// <> ダイヤモンド演算子とよぶっぽい。
//型推論とかなんとか
```
↑以外は知ってた。  
"\<? extends T>"　これなんなんだ.....

----

### <a  href="https://youtu.be/3tl0MO-Ftp0">【やさしくない!? Java】ジェネリクスの話３ OCJ-P Gold向け</a>
↑どうやって活用するんじゃい！

#### ■まとめ
ArrayListにNullがいれれる！それヌルポになるかもよ？
じゃあ、新しく作ってif(T == null)ではじけばええんや
#### ■一言
ArrayListにNullって入れれる事を初めて知った話する？ｗ
↑昔の自分何言ってんだ？Null入れれない奴なんてあったか？


----

### <a  href="https://youtu.be/_0d58J8_sG4">【やさしくない!? Java】ジェネリクスの話４ OCJ-P Gold向け</a>
↑
相称型を作るより、予め作られているものを見て  
「この記号なんやねん！」関係の話

#### ■まとめ
重要なのは、コンパイラがチェック出来るようにすること  
だから、
List\<Object> list = ArrayList<String>  
はできない。  

#### ■一言
待って、Number型ってマジであるの？
byte,int,long,float,double位しか知らんのだが  
↑こいつらは、元々Number型ってやつを継承して作られているっぽい（へぇ～  
知識が足りないのか、「何を当たり前の事を言っているんだ」という気持ちになった。型のそれ相応のチェックがあるわけで、継承してるからといって
許容したら意味ないんじゃね？と思った。  
多分、プログラム分かる、ポリモーフィズム分かるマンなら出てきそうな質問に回答してる感がある。







http://kusamakura.hatenablog.com/entry/2017/01/10/%E3%82%B7%E3%82%B0%E3%83%8D%E3%83%81%E3%83%A3%E3%81%AB%E3%80%81T_%E3%81%A8%E3%81%8B%E3%80%81K_%E3%81%A8%E3%81%8B%E6%9B%B8%E3%81%84%E3%81%A6%E3%81%82%E3%82%8B%E3%81%A8_API_%E3%81%9D%E3%81%A3%E9%96%89
これも読みたい


----
### <a  href="https://youtu.be/7vxCBzx1nss">【やさしくない!? Java】ジェネリクスの話５ OCJ-P Gold向け</a>
↑
List<?> ワイルドカードジェネリクス！

#### ■まとめ
List<?> が宣言できる！「= List/<Object>」とか多分何でも入れれる！  
でもAdd(null)しかできない！  
実用性は無いです。じゃあ、これを踏まえてもう少し狭めたやつを次回へ

#### ■一言
~~強そう！！~~  
<>の中にワイルドカードなんて入れれたんだ！  
イメージ的にはstring、文字列とかdate


----
### <a  href="https://youtu.be/seTslumbiw8">【やさしくない!? Java】ジェネリクスの話６ OCJ-P Gold向け</a>
↑
List<? extends Number>の話  
ｵﾏｴﾀﾞｧ!!初見分からん構文！

#### ■まとめ
・<?>は読み出し専用に使われたりするｯぽい。
・<? extends Number>でNumber型を継承してるInteger,DoubleもOKに出来る  
・<? super Number>で上と逆になって、Number以上のObjectもOKとなる(Integerはだめ)
・ワイルドカードを使うなら、結局Addは出来ない。しかし、メソッドとかの読み出しはOK

#### ■一言
なんて書いてあるかっていうと  
★　　 　<? extends Number> これが今回の肝  
class Integer extends Number　ちなみにこれはIntegerのクラス  
もうわかるね！そういう事  
Number型以下の型が使えるよって意味








# StreamAPI


## 『StreamAPI』とはコレクション操作をシンプルにするもの

いきなり例）  
```


```



## 参考にした資料(StreamAPI)

<a href="https://youtu.be/ECVfeSt2rPQ">【手書きプログラミング】ラムダ式とStreamAPI【Java】</a>
←