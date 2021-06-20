public class UpdateArray {

    public static void main(String[] args) {
        System.out.println("Start");

        int[] numberArray = { 1, 2, 3, 4, 5 };
        for (int number : numberArray) {
            System.out.println(number);
        }
        System.out.println();

        UpdateArray updateArray = new UpdateArray();
        updateArray.reverse(numberArray);

        for (int number : numberArray) {
            System.out.println(number);
        }

        System.out.println("End");
    }

    private void reverse(int[] numberArray) {
        int length = numberArray.length;

        for (int i = 0; i < length / 2; i++) {
            int tmp = numberArray[i];
            numberArray[i] = numberArray[length - i -1];
            numberArray[length - i-1] = tmp;
        }

        for (int number : numberArray) {
            System.out.println(number);
        }
        System.out.println();
    }

}
