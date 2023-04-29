package Test;

import java.util.Scanner;

public class Test {
    private Scanner scanner;
    private final String TEISU = "定数の書き方";

    public static void main(String[] args) {
        Test test = new Test();
        test.init();
        test.start();
        test.dispose();
    }

    private void init() {
        scanner = new Scanner(System.in);
    }

    private void dispose() {
        scanner.close();
    }
    private void start() {
        printf(TEISU);
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
    private String inputString() {
        return scanner.nextLine();
    }
    private int inputInt() {
        return scanner.nextInt();
    }
}
