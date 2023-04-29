package ForBeginners;

import java.util.Scanner;

public class Calculator {
    private final int FIRST_TARGET_NUM = 15;
    private final int SECOND_TARGET_NUM = 3;
    private final String MSG_ZENTEI = "演算対象の数字は" + FIRST_TARGET_NUM + "と" + SECOND_TARGET_NUM + "です\n";
    private final String MSG_INPUT = "次のどれかの演算子を入力してください(+,-,*):";
    private final String OPE_PLUS = "+";
    private final String OPE_MINUS = "-";
    private final String OPE_MULT = "*";
    private final String RESULT = "%s %s %s = %s";
    private Scanner scanner;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.init();
        calculator.start();
        calculator.dispose();
    }

    private void init() {
        scanner = new Scanner(System.in);
    }

    private void dispose() {
        scanner.close();
    }

    private void start() {

        printf(MSG_ZENTEI);
        printf(MSG_INPUT);
        String operator = inputString();

        switch (operator) {
            case OPE_PLUS:
                int result = FIRST_TARGET_NUM + SECOND_TARGET_NUM;
                printf(RESULT, FIRST_TARGET_NUM, operator, SECOND_TARGET_NUM, result);
                break;
            case OPE_MINUS:
                result = FIRST_TARGET_NUM - SECOND_TARGET_NUM;
                printf(RESULT, FIRST_TARGET_NUM, operator, SECOND_TARGET_NUM, result);
                break;
            case OPE_MULT:
                result = FIRST_TARGET_NUM * SECOND_TARGET_NUM;
                printf(RESULT, FIRST_TARGET_NUM, operator, SECOND_TARGET_NUM, result);
                break;
            default:
                break;
        }
    }

    private String inputString() {
        return scanner.nextLine();
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
