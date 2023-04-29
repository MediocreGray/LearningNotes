package ForBeginners;

import java.util.Scanner;

public class PositiveNumberAdder {
    private final String MSG_INPUT_REQ = "数値を入力してください：";
    private final String MSG_SUM = "合計：%s";
    private Scanner scanner;

    public static void main(String[] args) {
        PositiveNumberAdder positiveNumberAdder = new PositiveNumberAdder();
        positiveNumberAdder.init();
        positiveNumberAdder.start();
        positiveNumberAdder.dispose();
    }

    private void init() {
        scanner = new Scanner(System.in);
    }

    private void dispose() {
        scanner.close();
    }

    private void start() {
        int sun = 0;

        while (true) {
            printf(MSG_INPUT_REQ);
            int inputNum = inputInt();

            if(inputNum < 0)
            {
                break;
            }
            sun += inputNum;
        }
        printf(MSG_SUM,sun);
    }

    private int inputInt() {
        return scanner.nextInt();
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
