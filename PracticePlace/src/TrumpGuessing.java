import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TrumpGuessing {
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

    private Scanner scanner;
    private Random random;

    TrumpGuessing() {
        random = new Random();
    }

    public static void main(String[] args) {
        TrumpGuessing trumpGuessing = new TrumpGuessing();
        trumpGuessing.start();
    }

    public void start() {
        try {
            scanner = new Scanner(System.in);
            Suit answerSuit = getRandomSuit();
            String answerTrumpNumber = getRandomTrumpNumber();

            print(MSG_PICKANSWERCARD);

            // デバッグ用 答えの表示
            print("Debug：図柄＝%s, 数字＝%s\n", answerSuit, answerTrumpNumber);

            boolean isCorrectSuitGuess = startSuitGuess(answerSuit);
            if (isCorrectSuitGuess == false) {
                print(MSG_GAMEOVER, answerSuit, answerTrumpNumber);
                return;
            }
            print(MSG_CORRECT_SUIT, answerSuit);

            boolean isCorrectNumberGuess = startTrumpNumberGuess(answerTrumpNumber);
            if (isCorrectNumberGuess == false) {
                print(MSG_GAMEOVER, answerSuit, answerTrumpNumber);
                return;
            }
            print(MSG_CORRECT_SUITANDNUMBER, answerSuit, answerTrumpNumber);

        } finally {
            scanner.close();
        }
    }

    private void print(String format, Object... args) {
        System.out.printf(format, args);
    }

    private boolean startSuitGuess(Suit answerSuit) {
        int tryCount = 0;
        Suit guessedSuit;

        print(MSG_START_GUESS_SUIT);
        showSuitChoices();

        while (isSuitGuessLimit(++tryCount)) {
            guessedSuit = recursiveInputSuit();

            if (guessedSuit == answerSuit) {
                return true;
            }

            print(MSG_INCORRECT, guessedSuit);
        }
        return false;
    }

    private boolean startTrumpNumberGuess(String answerTrumpNumber) {
        int tryCount = 0;
        String guessedTrumpNumber = "";

        print(MSG_START_GUESS_NUMBER);

        while (isNumberGuessLimit(++tryCount)) {
            guessedTrumpNumber = recursiveInputTrumpNumber();

            if (guessedTrumpNumber.equals(answerTrumpNumber)) {
                return true;
            }

            print(MSG_INCORRECT, guessedTrumpNumber);
        }
        return false;
    }

    private boolean isSuitGuessLimit(int count) {
        return count <= TRYLIMIT_SUIT;
    }

    private boolean isNumberGuessLimit(int count) {
        return count <= TRYLIMIT_NUMBER;
    }

    private Suit getRandomSuit() {
        return Suit.of(random.nextInt(Suit.values().length));
    }

    private String getRandomTrumpNumber() {
        return trumpNumberArray[random.nextInt(trumpNumberArray.length)];
    }

    private Suit recursiveInputSuit() {
        print(MSG_WHICH);
        Suit inputedSuit = convertFromStringToSuit(inputLine());

        if (null == inputedSuit) {
            print(MSG_SELECTAGAIN_SUIT);
            showSuitChoices();
            return recursiveInputSuit();
        }

        return inputedSuit;
    }

    private String recursiveInputTrumpNumber() {
        print(MSG_WHICH);
        String inputedTrumpNumber = inputTrumpNumber();

        if (inputedTrumpNumber == null || inputedTrumpNumber.isEmpty()) {
            print(MSG_SELECTAGAIN_NUMBER);
            showTrumpNumberChoices();
            return recursiveInputTrumpNumber();
        }

        return inputedTrumpNumber;
    }

    private String inputTrumpNumber() {
        String line = inputLine();

        if (Arrays.asList(trumpNumberArray).contains(line)) {
            return line;
        }
        return "";
    }

    private Suit convertFromStringToSuit(String str) {
        int number;

        try {
            number = Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }

        return convertFromNumberToSuit(number);
    }

    private Suit convertFromNumberToSuit(int number) {
        Suit resSuit = null;

        try {
            resSuit = Suit.of(number);
        } catch (Exception e) {
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
}