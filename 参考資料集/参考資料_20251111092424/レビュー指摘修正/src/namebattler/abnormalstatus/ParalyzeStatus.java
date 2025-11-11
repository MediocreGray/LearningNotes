// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.abnormalstatus;

import java.util.Random;

import namebattler.job.Player;

// [必須] JavaDocのクラスコメントを記載しましょう
public class ParalyzeStatus extends AbnormalStatus {
    private static final int PARALYZE_INCAPACITATE_RATE = 20;
    private static final int ONE_HUNDRED_PERCENT = 100;
    private static final String INCAPACITATE_SUCCESS_MESSAGE = "%s は からだがしびれて うごけない!\n";

    /*
     * コンストラクタ
     */
    public ParalyzeStatus() {
        this.statusName = "まひ";
    }

    /**
     * 麻痺状態のプレイヤーを確率で行動不能にする
     * 
     * @param target 対象のプレイヤー
     */
    public static boolean isIncapable(Player target) {
        Random rand = new Random();
        if (PARALYZE_INCAPACITATE_RATE > rand.nextInt(ONE_HUNDRED_PERCENT)) {
            System.out.printf(INCAPACITATE_SUCCESS_MESSAGE, target.getName());
            return true;
        }
        return false;

        // [必須] 最後の;は不要です
    };
}
