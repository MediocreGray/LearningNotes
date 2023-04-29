package ForBeginners;

public class WhileLoopBreaker {

    public static void main(String[] args) {
        WhileLoopBreaker whileLoopBreaker = new WhileLoopBreaker();
        whileLoopBreaker.start();
    }

    private void start() {
        int counter = 0;

        while (counter < 10) {
            if (5 <= counter)
                break;

            counter++;
            printf("%d\n", counter);
        }

        printf("counter = %d \n", counter);
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
