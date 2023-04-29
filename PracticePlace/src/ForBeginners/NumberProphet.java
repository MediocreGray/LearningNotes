package ForBeginners;

import java.util.Random;
import java.util.Scanner;

public class NumberProphet {
    private final int RANGE_FOR_CORRECT = 7;
    private final String MSG_CORRECT = "正解！";
    private final String MSG_INCORRECT = "不正解";
    private Scanner scanner;
    private Random rand;

    public static void main(String[] args) {
        NumberProphet np = new NumberProphet();
        np.init();
        np.start();
        np.dispose();
    }

    private void init() {
        scanner = new Scanner(System.in);
        rand = new Random();
    }

    private void dispose() {
        scanner.close();
    }

    private void start() {
        int correct = getCorrectRandomNumber();
        int prediction = inputInt();

        if (correct == prediction) {
            printf(MSG_CORRECT);
        } else {
            printf(MSG_INCORRECT);
        }
    }

    private int inputInt() {
        return scanner.nextInt();
    }

    private int getCorrectRandomNumber() {
        return rand.nextInt(RANGE_FOR_CORRECT);
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
