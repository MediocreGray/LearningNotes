
# ラムダ式
StreamAPIを最初に学ぼうと思ったが、
どうやらラムダ式ありきのStreamAPIっぽいので
こっちから学んでいく


---
## <a  href="https://youtu.be/Lhr9ZCmT1b0">【やさしくない!? Java】 ラムダ式の話１ OCJ-P Gold向け</a>
概要：なんでラムダ式が必要なの？

### ■まとめ  
なぜ必要か、昔のインナークラス(?)のお話をすることに...  
今回は情報隠蔽の仕方(Factory)をおさらい。

```
=========
Package com.sample
    ★インターフェース。外部からでも見える
    public Interface Sample{execute();}
    ★↑を実装したSampleImpl。publicではないので外部からは見えない
    class SampleImpl implements Sample{execute(){println("Hello")}}
    ★ファクトリークラス。外部から見えて、SampleImpleも見えるので作れる。
    public class Factory{public static Sample create(){return new SampleImpl} }
==========

main()
{
    Sample sample = Factory.create();
    sample.execute();   //Hello
}


```

---
## <a  href="https://youtu.be/C8ajfSSjhR8">【やさしくない!? Java】 ラムダ式の話２ OCJ-P Gold向け</a>
概要：インナークラストは何ぞやと

### ■まとめ  
アクセス修飾子がないと、Package内で使いたい放題じゃん？うじゃうじゃいるじゃん？    
でもPackage内だけど、こいつには使われたくないな～  
そんな時にインナークラス。

```
=========
Package com.sample
    public class Factory{
        public static Sample create(){return new SampleImpl} 

        ★インナークラス。これでﾌｧｸﾄﾘｰ内でしか使えない
        private class InnerSample(){
            execute(){
                println("Hello")
            }
        }
    }
==========
```

### ■一言  
今あるものは触らずに、新しく追加出来る。  
そう。それがFactoryクラス！  
邪魔なだけではと思っていたけど、  
そんなことはなさそう。  


---
## <a  href="https://youtu.be/wLbwkRhkoyY">【やさしくない!? Java】 ラムダ式の話３ OCJ-P Gold向け</a>
概要：無名クラスってなんだ？

### ■まとめ  
情報隠蔽はなぜ？  
何でもかんでも使えるならスパゲッティにする奴が出てくるから。  

InnnerSampleあまり使えなくね？宣言要らんわ。→無名クラス
```
public class Factory{
    public static Sample create(){
        return new Sample() {   ★I/Fでも実際に中身を書けばクラスとして扱われる
            @override
            public execute(){ println("我無名クラス也"); }
        }
    }
}
```

### ■一言  
わぁ、こいつインターフェースでクラス作り始めやがった！！  
んで、これが無名クラス。  
あ、ちなみに次回やっとラムダ式っぽい。



---
## <a  href="https://youtu.be/hf2tnpev6Ig">【やさしくない!? Java】 ラムダ式の話４ OCJ-P Gold向け</a>
概要：ｲﾝﾅｰｸﾗｽ、無名ｸﾗｽ、幅取るし邪魔だし何とかならん？→ラムダ式

### ■まとめ  
```
public class Factory{
    public static Sample create(){
        return () -> {
            println("lambda")
        };
    }
}
```

### ■一言  
~~ha???WTF??What's wrong with you???~~  
取り乱しそうになったが、  
Sampleはexecute一個しかメソッドを持っていないので、
そいつさえわかってしまえば書ける代物。  
(えぇ、クラスに一個だけのメソッドってあり得るんか?次回もあるので今は素人は黙っておく。)



---
## <a  href="https://youtu.be/xj-IGnqH4EY">【やさしくない!? Java】 ラムダ式の話５ OCJ-P Gold向け</a>
概要：関数型インターフェース

### ■まとめ  
Sampleの様に関数しかない奴を  
★関数型インターフェースと呼ぶ。  
しかも、なぁんと!関数型インターフェースは一つのメソッドしか許さないらしい。  
(イメージとしてクラスってのは何かの集まりであって、メソッド一つだけてお前...っていう感想が  

Q.でも複数人でやってたら関数型I/F君を弄られて二つメソッドになったりしないの？
A.「@FunctionalInterface」を付けるとエラーを出してくれます！  
(見たことある～…そういう意味だったのか。)

### ■一言  
最後らへん、関数型I/Fはもう既に用意されていて  
↓こんな感じ
| 　　　 | 引数あり                       | 引数なし         | 
| --------- | ------------------------------ | ---------------- | 
| 返り値あり｜ | Function,Predicate(boolを返す) | Supplier         | 
| 返り値なし｜| Consumer                       | 実質今回のSample | 


---
## <a  href="https://youtu.be/CShh-twzdSw">【やさしくない!? Java】 ラムダ式の話６ OCJ-P Gold向け</a>
概要：関心ごとについて

### ■まとめ  
★コードの流れとして  
↓ 最初にやる処理  
↓ 変わる処理  ←　これが関心事。ここをいろいろ変えれるようにしようねって話  
↓ 最後にやる処理  

### ■一言  
メソッドとかIFとかこんな感じの考え方だよね。  
知らんけど



---
## <a  href="https://youtu.be/ucl3-n2zpeg">【やさしくない!? Java】 ラムダ式の話７ OCJ-P Gold向け</a>
概要：関心ごとについて実際にプログラム

### ■まとめ  
分離してIFの引数を渡して実行！
これがストラテジーだｧ！！

### ■一言  
そっすね。




---
## <a  href="https://youtu.be/aS_EVVrDyow">【やさしくない!? Java】 ラムダ式の話８ OCJ-P Gold向け</a>
概要：ラムダ式の前にメソッドチェーンの話

### ■まとめ  
コンストラクタの初期化って引数多すぎじゃない？→メソッドチェーン  
昔はSetterをめちゃくちゃ使ってた。
今は
```
public Item id (String id)
{
    this.id = id;
    return this;
}
…↑をいっぱいすると

Item item = new Item().id("A100").name("LX100G").price(100);
みたいな感じで、引数より分かりやすくなる。
```

### ■一言  
へぇ～！  
でももし、.nameとか入れなかったらどうなるの？
入らないままなの？それっていいの？
コンストラクタだったら必ず入れなければならなくなるけど

---
## <a  href="https://youtu.be/K1WT0CZJhbM">【やさしくない!? Java】 ラムダ式の話９ OCJ-P Gold向け</a>
概要：メソッドチェーンを踏まえてラムダ式の前

### ■まとめ  
Consumer使ったあたりから何を言ってるんだ?  
con.accept(item) ???  
えぇっと、メソッドが一つしかないから
Item.save(ここの中がラムダでかける。)  

ちがう、saveの中にItem item = new Item();が気に食わない(分からない)所だ  
con\<Item>のacceptに空のitemを入れてる...??もうどうなってるか分かんねぇなコレ。

### ■一言  
えぇ、一個前の方が分かりやすかった。。。
ラムダ式やっぱ見づらいし複雑な気がするよぉ...  


---

## まとめ  
結局の所、ラムダ式そのものを学んだというより、  
その背景を学んだ気がする。（最後何言ってるか分からなかったし...    
関数型インターフェースとか、メソッドチェーンとか知れただけでも良しとしよう。



---
## <a  href="https://udomomo.hatenablog.com/entry/2019/01/26/152346">Javaの関数型インターフェースとラムダ式・Streamの関係</a>

### ■まとめ  
> ここまできて、そもそもラムダ式の定義を勘違いしていたことがわかった。ラムダ式は単なる「関数の簡潔な書き方」ではなく、「メソッドが1つだけ定義されたインターフェースを、実装する側でより簡潔に使う方法」 だった。

関数型インターフェースの為にあるようなものって事よな。
そんな気はしてた。

>  このFunctionとは、java.util.functionで定義されている汎用の関数型インターフェースの1つであり、 あるデータ型の値1つを入力として受け取り、あるデータ型の値1つを出力する という処理をするためのもの。
Functionインターフェースの中をのぞいてみるとこのようになっている。

????????  
ここの部分、Functionの中身は何言っているか分からなかった。













---
## <a  href=""></a>

### ■まとめ  

### ■一言  
