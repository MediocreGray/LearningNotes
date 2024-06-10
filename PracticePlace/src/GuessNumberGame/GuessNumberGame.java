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
