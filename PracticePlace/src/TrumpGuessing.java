import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TrumpGuessing {

    // #region 定数
    private static final String MSG_PICKANSWERCARD = "トランプを選んだよ\n";
    private static final String MSG_GUESS_SUIT = "トランプの図柄を当ててね\n";
    private static final String MSG_GUESS_NUMBER = "次は数字を当ててね\n";
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

        public static Suit valueOf(final int value) {
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
    private Suit answerSuit;
    private String answerTrumpNumber;
    private boolean isCorrectSuitGuess;
    private boolean isCorrectNumberGuess;
    private Scanner scanner;
    private Random random;
    // #endregion

    // #region コンストラクタ
    TrumpGuessing() {
        answerSuit = null;
        answerTrumpNumber = null;
        isCorrectSuitGuess = false;
        isCorrectNumberGuess = false;
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

            this.answerSuit = this.getRandomSuit();
            this.answerTrumpNumber = this.getRandomTrumpNumber();
            System.out.printf(MSG_PICKANSWERCARD);

            this.doSuitGuess();

            if (!this.isCorrectSuitGuess) {
                System.out.printf(MSG_GAMEOVER, this.answerSuit, this.answerTrumpNumber);
                return;
            }
            System.out.printf(MSG_CORRECT_SUIT, this.answerSuit);

            this.doTrumpNumberGuess();

            if (!this.isCorrectNumberGuess) {
                System.out.printf(MSG_GAMEOVER, this.answerSuit, this.answerTrumpNumber);
                return;
            }
            System.out.printf(MSG_CORRECT_SUITANDNUMBER, this.answerSuit, this.answerTrumpNumber);
        } finally {
            scanner.close();
        }
    }

    private void doSuitGuess() {
        int tryCount = 0;
        Suit guessedSuit;

        System.out.printf(MSG_GUESS_SUIT);
        this.showSuitChoices();

        while (++tryCount <= TRYLIMIT_SUIT) {
            guessedSuit = this.recursiveInputSuit();

            if (guessedSuit == this.answerSuit) {
                this.setIsCorrectSuitGuess(true);
                return;
            }

            System.out.printf(MSG_INCORRECT, guessedSuit);
        }
        this.setIsCorrectSuitGuess(false);
    }

    private void doTrumpNumberGuess() {
        int tryCount = 0;
        String guessedTrumpNumber = "";

        System.out.printf(MSG_GUESS_NUMBER);

        while (++tryCount <= TRYLIMIT_NUMBER) {
            while (true) {
                System.out.printf(MSG_WHICH);
                guessedTrumpNumber = this.inputTrumpNumber();

                if (guessedTrumpNumber != null && !guessedTrumpNumber.isEmpty()) {
                    break;
                }

                System.out.printf(MSG_SELECTAGAIN_NUMBER);
                this.showTrumpNumberChoices();
            }

            if (guessedTrumpNumber.equals(this.answerTrumpNumber)) {
                this.setIsCorrectNumberGuess(true);
                return;
            }

            System.out.printf(MSG_INCORRECT, guessedTrumpNumber);
        }
        this.setIsCorrectNumberGuess(false);
    }

    private void setIsCorrectNumberGuess(boolean isCorrect) {
        this.isCorrectNumberGuess = isCorrect;
    }

    private void setIsCorrectSuitGuess(boolean isCorrect) {
        this.isCorrectSuitGuess = isCorrect;
    }

    private Suit getRandomSuit() {
        return Suit.valueOf(random.nextInt(Suit.values().length));
    }

    private String getRandomTrumpNumber() {
        return trumpNumberArray[random.nextInt(trumpNumberArray.length)];
    }

    private Suit recursiveInputSuit() {
        System.out.printf(MSG_WHICH);
        Suit inputedSuit = this.convertFromStringToSuit(this.inputLine());

        if (null == inputedSuit) {
            System.out.printf(MSG_SELECTAGAIN_SUIT);
            this.showSuitChoices();
            return recursiveInputSuit();
        }

        return inputedSuit;
    }

    private String inputTrumpNumber() {
        String line = this.inputLine();

        if (Arrays.asList(trumpNumberArray).contains(line)) {
            return line;
        }

        // System.out.printf("Debug：値が存在しない\n");
        return "";
    }

    private Suit convertFromStringToSuit(String str) {
        int number;

        try {
            number = Integer.parseInt(str);
        } catch (Exception e) {
            System.out.printf("Debug：数値に変換できません\n");
            return null;
        }

        return this.convertFromNumberToSuit(number);
    }

    private Suit convertFromNumberToSuit(int number) {
        Suit resSuit = null;

        try {
            resSuit = Suit.valueOf(number);
        } catch (Exception e) {
            System.out.printf("Debug：柄の範囲の数値を入力してください\n");
            return null;
        }

        return resSuit;
    }

    private void showSuitChoices() {
        for (Suit suit : Suit.values()) {
            System.out.printf(MSG_SHOW_SUITPAIR, suit.ordinal(), suit.name());
        }
    }

    private void showTrumpNumberChoices() {
        System.out.printf(MSG_SHOW_TRUMPNUMBER, String.join(",", trumpNumberArray));
    }

    private String inputLine() {
        return scanner.nextLine();
    }
    // #endregion
}