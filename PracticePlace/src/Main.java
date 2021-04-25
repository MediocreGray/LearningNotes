import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Main {
    
    public static void main(String[] args) {
        double companyGrowRate = 123456.78935465;


        DecimalFormat pFromat = new DecimalFormat("0,000.##%");
        // String message = String.format("%s",pFromat.format(companyGrowRate));
        String message = pFromat.format(companyGrowRate);
        

        System.out.println(message);

    }

}
