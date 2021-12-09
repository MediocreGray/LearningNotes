import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TrumpGuessing {

    // #region 定数
    private static final String MSG_PICKANSWERCARD = "トランプを選んだよ\n";
    private static final String MSG_START_GUESS_SUIT = "トランプの図柄を当ててね\n";
    private static final String MSG_START_GUESS_NUMBER = "次は数字を当ててね\n";
    private static final String MSG_WHICH = "どれだと思う？：";
    private static final String MSG_SHOW_SUITPAIR = "%d:%s\n";
    private static final String MSG_SHOW_TRUMPNUMBER = "%s\n";
    private static final String MSG_CORRECT_SUIT = "正解！図柄は%sだよ\n";
    private static final String MSG_CORRECT_SUITANDNUMBER = "正解！%sの%sだよ\n";
    private static final String MSG_INCORRECT = "残念！%sじゃないよ\n";
    private static final String MSG_SELECTAGAIN_SUIT = "以下のSuitの数値から選んでください\n";
    private static final String MSG_SELECTAGAIN_NUMBER = "以下の数字から選んでください\n";
    private static final String MSG_GAMEOVER = "GAMEOVER 答え:図柄=%s 数字=%s\n";

    private static final int TRYLIMIT_SUIT = 2;
    private static final int TRYLIMIT_NUMBER = 5;

    private enum Suit {
        HEART, DIAMOND, SPADE, CLOVER;

        // REV：ofだけでいい ProcessとかJudgeとかあまり意味のない言葉は使わない方がいい
        public static Suit of(final int value) {
            for (Suit suit : Suit.values()) {
                if (suit.ordinal() == value) {
                    return suit;
                }
            }
            return null;
        }
    }

    private static final String[] trumpNumberArray = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q",
            "K" };
    // #endregion

    // #region フィールド

    // REV：PGの重要度ランキング
    // 読みやすさ ここはできてる！
    // メンテナンスのしやすさ

    // private Suit answerSuit;
    // private String answerTrumpNumber;
    // private boolean isCorrectSuitGuess;
    // private boolean isCorrectNumberGuess;
    private Scanner scanner;
    private Random random;
    // #endregion

    // #region コンストラクタ
    TrumpGuessing() {
        random = new Random();
    }
    // #endregion

    // #region メソッド
    public static void main(String[] args) {
        TrumpGuessing trumpGuessing = new TrumpGuessing();
        trumpGuessing.start();
    }

    public void start() {
        try {
            this.scanner = new Scanner(System.in);

            Suit answerSuit = this.getRandomSuit();
            String answerTrumpNumber = this.getRandomTrumpNumber();
            // REV：↓むきだし
            // print(MSG_PICKANSWERCARD);
            // print("Debug：図柄＝%s, 数字＝%s\n",answerSuit, answerTrumpNumber);
            this.print(MSG_PICKANSWERCARD);
            this.print("Debug：図柄＝%s, 数字＝%s\n", answerSuit, answerTrumpNumber);

            // ↓のような奴があると工数がかかる
            // 名前がこうで、この引数で渡して～してるんだろうなぁ
            boolean isCorrectSuitGuess = this.doSuitGuess(answerSuit);

            
            if (!isCorrectSuitGuess) {
                print(MSG_GAMEOVER, answerSuit, answerTrumpNumber);
                return;
            }
            print(MSG_CORRECT_SUIT, answerSuit);

            boolean isCorrectNumberGuess = this.doTrumpNumberGuess(answerTrumpNumber);

            if (!isCorrectNumberGuess) {
                print(MSG_GAMEOVER, answerSuit, answerTrumpNumber);
                return;
            }
            print(MSG_CORRECT_SUITANDNUMBER, answerSuit, answerTrumpNumber);
        } finally {
            scanner.close();
        }
    }

    private void print(String format, Object... args) {
        print(format, args);
    }

    private boolean doSuitGuess(Suit answerSuit) {
        int tryCount = 0;
        Suit guessedSuit;

        print(MSG_START_GUESS_SUIT);
        this.showSuitChoices();

        // REV：↓isLimit()でメソッドで伝える
        while (this.isLimit(TRYLIMIT_SUIT, ++tryCount)) {
            guessedSuit = this.recursiveInputSuit();

            if (guessedSuit == answerSuit) {
                return true;
            }

            print(MSG_INCORRECT, guessedSuit);
        }
        return false;
    }

    private boolean doTrumpNumberGuess(String answerTrumpNumber) {
        int tryCount = 0;
        String guessedTrumpNumber = "";

        print(MSG_START_GUESS_NUMBER);

        // while (++tryCount <= TRYLIMIT_NUMBER) {
        // // REV：普通にかこうぜ nullorempty
        // while (true) {
        // print(MSG_WHICH);
        // guessedTrumpNumber = this.inputTrumpNumber();

        // // REV：↓nullorempty()的な
        // if (guessedTrumpNumber != null && !guessedTrumpNumber.isEmpty()) {
        // break;
        // }

        // print(MSG_SELECTAGAIN_NUMBER);
        // this.showTrumpNumberChoices();
        // }

        while (this.isLimit(TRYLIMIT_NUMBER, ++tryCount)) {
            guessedTrumpNumber = this.recursiveInputTrumpNumber();

            if (guessedTrumpNumber.equals(answerTrumpNumber)) {
                return true;
            }

            print(MSG_INCORRECT, guessedTrumpNumber);
        }
        return false;
    }

    private boolean isLimit(int limit, int count) {
        return count <= limit;
    }

    private Suit getRandomSuit() {
        return Suit.of(random.nextInt(Suit.values().length));
    }

    private String getRandomTrumpNumber() {
        return trumpNumberArray[random.nextInt(trumpNumberArray.length)];
    }

    private Suit recursiveInputSuit() {
        print(MSG_WHICH);
        Suit inputedSuit = this.convertFromStringToSuit(this.inputLine());

        if (null == inputedSuit) {
            print(MSG_SELECTAGAIN_SUIT);
            this.showSuitChoices();
            return recursiveInputSuit();
        }

        return inputedSuit;
    }

    private String recursiveInputTrumpNumber() {
        print(MSG_WHICH);
        String inputedTrumpNumber = this.inputTrumpNumber();

        if (inputedTrumpNumber == null || inputedTrumpNumber.isEmpty()) {
            print(MSG_SELECTAGAIN_NUMBER);
            this.showTrumpNumberChoices();
            return this.recursiveInputTrumpNumber();
        }

        return inputedTrumpNumber;
    }

    private String inputTrumpNumber() {
        String line = this.inputLine();

        if (Arrays.asList(trumpNumberArray).contains(line)) {
            return line;
        }

        // print("DebugLog：値が存在しない\n");
        return "";
    }

    private Suit convertFromStringToSuit(String str) {
        int number;

        try {
            number = Integer.parseInt(str);
        } catch (Exception e) {
            // print("DebugLog：数値に変換できません\n");
            return null;
        }

        return this.convertFromNumberToSuit(number);
    }

    private Suit convertFromNumberToSuit(int number) {
        Suit resSuit = null;

        try {
            resSuit = Suit.of(number);
        } catch (Exception e) {
            // print("DebugLog：柄の範囲の数値を入力してください\n");
            return null;
        }

        return resSuit;
    }

    private void showSuitChoices() {
        for (Suit suit : Suit.values()) {
            print(MSG_SHOW_SUITPAIR, suit.ordinal(), suit.name());
        }
    }

    private void showTrumpNumberChoices() {
        print(MSG_SHOW_TRUMPNUMBER, String.join(",", trumpNumberArray));
    }

    private String inputLine() {
        return scanner.nextLine();
    }
    // #endregion
}