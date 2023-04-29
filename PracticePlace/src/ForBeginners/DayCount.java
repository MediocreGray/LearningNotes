package ForBeginners;

public class DayCount {
    private final String MSG_DAY = "%s日目\n";
    private final int END_DAY = 27;

    public static void main(String[] args) {
        DayCount dayCount = new DayCount();
        dayCount.start();
    }

    private void start() {
        int day = 1;
        while (day <= END_DAY) {
            printf(MSG_DAY, day);
            day++;
        }
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
