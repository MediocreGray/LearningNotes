// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.abnormalstatus;

import namebattler.job.Player;

// [必須] JavaDocのクラスコメントを記載しましょう
public class PoisonStatus extends AbnormalStatus {

    private static final String POISON_DOT_MESSAGE = "%s は どくに おかされている!\n";
    private static final int DOT_DAMAGE = 10;

    /*
     * コンストラクタ
     */
    public PoisonStatus() {
        this.statusName = "どく";
    }

    /**
     * 毒状態のプレイヤーに毒によるDOTダメージを与える
     * 
     * @param target 対象のプレイヤー
     */
    public static void dealDotDamageTo(Player target) {
        System.out.printf(POISON_DOT_MESSAGE, target.getName());
        target.takeDamage(DOT_DAMAGE);
        // [必須] 最後の;は不要です
    };
}
