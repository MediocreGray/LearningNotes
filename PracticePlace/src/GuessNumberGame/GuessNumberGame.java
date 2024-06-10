package GuessNumberGame;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    // #region 定数
    private final int MAX_TRY_ANSWERS = 5;
    private final int MAX_GUESS_NUMBER = 100;

    private final String MSG_GUESS_NUMBER = "数字を当ててみてね。\n";
    private final String MSG_TRY_ANSWERS = "答えられるのは%d回までだよ。\n";
    private final String MSG_ANSWER_NUMBER = "%d回目：";
    private final String MSG_MORE_NUMBER = "もっと大きい数字だよ\n";
    private final String MSG_LESS_NUMBER = "もっと小さい数字だよ\n";
    private final String MSG_CORRECT = "すごい！！%d回で当てられちゃった！\n";
    private final String MSG_INCORRECT = "残念！！ 正解は %d でした！\n";
    // #endregion

    public static void main(String[] args) {
        GuessNumberGame GuessNumberGame = new GuessNumberGame();
        GuessNumberGame.start();
    }

    private void start() {
        // リソースの初期化
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 始めのメッセージ
        System.out.printf(MSG_GUESS_NUMBER);
        System.out.printf(MSG_TRY_ANSWERS, MAX_TRY_ANSWERS);

        // 回答開始
        boolean isCorrect = false;
        int guessNumber = random.nextInt(MAX_GUESS_NUMBER);
        for (int tryNumber = 1; tryNumber <= MAX_TRY_ANSWERS; tryNumber++) {

            System.out.printf(MSG_ANSWER_NUMBER, tryNumber);
            int answerNumber = -1;
            String input = scanner.nextLine();

            // 入力チェック
            try {
                if (this.isNumber(input)) {
                    answerNumber = Integer.parseInt(input);
                } else {
                    // 数字ではない場合、再度入力させる
                    tryNumber--;
                    continue;
                }
            } catch (NumberFormatException nfex) {
                scanner.close();
            }

            // 正答チェック
            if (answerNumber == guessNumber) {
                // 正解
                isCorrect = true;
                System.out.printf(MSG_CORRECT, tryNumber);
                break;
            }

            if (guessNumber > answerNumber) {
                // 回答が大きい
                System.out.printf(MSG_MORE_NUMBER);
            } else {
                // 回答が小さい
                System.out.printf(MSG_LESS_NUMBER);
            }
        }

        // 失敗
        if (false == isCorrect) {
            System.out.printf(MSG_INCORRECT, guessNumber);
        }

        // リソースの解放
        scanner.close();
    }

    /**
     * 数値かどうか
     */
    private boolean isNumber(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }
}

/*
 * レビュー内容
 * 
 * ①警告が出ている場合はしっかり対応する
 * ー警告が出ないような実装にすべき
 * ー今回の場合、parseIntでエラーになるとcloseに行く前に終了してしまい、scannerがcloseされないというもの
 * ー数字が入力されるまで入力を繰り返すメソッドを作ると良い
 * 例）
 * private int inputNumber(Scanner scanner, int tryNumber) {
 * while (true) {
 * System.out.printf(MSG_ANSWER_NUMBER, tryNumber);
 * try {
 * return Integer.parseInt(scanner.nextLine());
 * } catch (NumberFormatException e) {
 * System.out.println("数字を入力してください");
 * continue;
 * }
 * }
 * }
 * 
 * ②メソッドにはコメントを
 * ーメソッドが、何を渡せばどう処理してくれて何を返してくれるかを書くことで第三者や将来の自分が見たときにぱっと見で分かるようにする（また、
 * 処理の正しさの再確認ができるというメリットもある）
 * ーJavadocを使うのがベター
 * 
 * ③for文のカウンタ変数の宣言と初期化は一般的にはアンチパターンとなる
 * ー理由①：可読性の低下
 * ーぱっと見で初期値が何か分かりにくいため、コードが読み解きづらくなる
 * ー理由②：変数のスコープが大きくなる
 * ー省略した場合、カウンタ変数はfor文を抜けた後も生存する形となり、その変数が思わぬタイミングで使用/書き換えされるリスクが出てしまう（
 * 基本的にfor文のカウンタ変数はfor文内にとどめるのがベター）
 * 
 * ④else ifは最小限になるように実装すると良い
 * ーelse ifは数が多いと可読性が落ちるため、最小限にとどめる工夫をする
 * ー正解だった場合はbreakとなるため、else ifは不要（早期return）
 * 
 * ⑤ifの判定式などの右辺と左辺にどちらを設定するかもルールがある
 * ー現場のコーディング規約にもよるがよくあるのは比較される側が左辺、比較する側が右辺
 * 
 */
