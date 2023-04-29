package ForBeginners;

public class Aggregator {
    private final String MSG_SUM = "配列の要素 %d つの合計 = %d\n";
    private final String MSG_AVERAGE = "配列の要素 %d つの平均 = %.2f\n";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        aggregator.start();
    }


    private void start() {

        int[] numbers = { 3, 4, 5, -5, 0, 12 };
        int sum = 0;

        int numberOfElements = numbers.length;
        for (int number : numbers) {
            sum += number;
        }

        double average = (double) sum / (double) numberOfElements;

        printf(MSG_SUM, numberOfElements, sum);
        printf(MSG_AVERAGE, numberOfElements, average);
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
