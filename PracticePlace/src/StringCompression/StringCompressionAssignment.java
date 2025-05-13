package StringCompression;

import java.util.Random;
import java.util.Scanner;

public class StringCompressionAssignment {

    public static void main(String[] args) {
        StringCompressionAssignment StringCompressionAssignment = new StringCompressionAssignment();
        StringCompressionAssignment.start();
    }

    private void start() {

        StringsCompressor stringsCompressor = new StringsCompressor();
        String text = "";

        System.out.println("実行例 1");
        text = stringsCompressor.encode("AAABBCDDD");
        System.out.println(text);


        System.out.println("実行例 1");
        text = stringsCompressor.encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
        System.out.println(text);

        System.out.println("実行例 2");
        text = stringsCompressor.encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFGG");
        System.out.println(text);
    }
    
}
