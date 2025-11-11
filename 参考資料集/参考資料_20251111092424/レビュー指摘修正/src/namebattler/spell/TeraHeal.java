// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.spell;

import namebattler.job.Player;

// [意見] ヒールとパラメータ以外全く同じ処理なので、自分だったら共通化するかなと思います
/**
 * 呪文：テラヒールを管理するクラス
 */
public class TeraHeal extends Spell {
    private static final String SPELL_NAME = "テラヒール";
    private static final int MP_COST = 40;
    private static final int HEAL_AMOUNT = 70;

    /**
     * コンストラクタ
     */
    public TeraHeal() {
        this.name = SPELL_NAME;
        this.mpCost = MP_COST;
    }

    /**
     * テラヒールを発動する
     * MPを支払い、HPを回復する
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    @Override
    public void activate(Player caster, Player target) {
        target.gainHp(HEAL_AMOUNT);
    }
}
