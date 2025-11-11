// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.spell;

import java.util.Random;

import namebattler.job.Player;

/**
 * 呪文：ギガサンダーを管理するクラス
 */
public class GigaThunder extends Spell {
    private static final String SPELL_NAME = "ギガサンダー";
    private static final int MP_COST = 40;
    private static final int MIN_DAMAGE = 10;
    private static final int DAMAGE_RANGE = 61;

    // [意見] 複数の呪文クラスで使用されているので、基底クラスで共通変数にしても良いかと思います
    // 現状だと、呪文クラスが増えるたびにインスタンスが増える可能性があります
    Random rand = new Random();

    /**
     * コンストラクタ
     */
    public GigaThunder() {
        this.name = SPELL_NAME;
        this.mpCost = MP_COST;
    }

    /**
     * ギガサンダーを発動する
     * MPを支払い、防御無視の乱数ダメージを与える
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    @Override
    public void activate(Player caster, Player target) {
        int damage = MIN_DAMAGE + rand.nextInt(DAMAGE_RANGE);
        target.takeDamage(damage);
    }
}
