// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.spell;

import namebattler.job.Player;

/**
 * 呪文の基底クラス
 */
public abstract class Spell {
    // [Q] 状態異常クラスなどと同様に、基底クラスのコンストラクタでメンバ変数を初期化していない意図はありますか？
    // 呪文名と消費MPはクラスを使用する前に設定して欲しい初期パラメータだと思うので、コンストラクタで初期化を行うのがオススメです
    /** 呪文名 */
    protected String name;
    /** 消費MP */
    protected int mpCost;

    /**
     * 呪文名を取得
     * 
     * @return 呪文名
     */
    public String getName() {
        return name;
    }

    /**
     * 消費MPを取得
     * 
     * @return 消費MP
     */
    public int getMpCost() {
        return mpCost;
    }

    /**
     * 呪文を発動する抽象メソッド
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    public abstract void activate(Player caster, Player target);

}
