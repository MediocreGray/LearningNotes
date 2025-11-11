package stringcompression;

/**
 * 文字列圧縮課題の実行クラス
 */
public class StringCompressionAssignment {

    /**
     * メインメソッド
     */
    public static void main(String[] args) {
        StringCompressionAssignment.start();
    }

    /**
     * 処理開始
     */
    private static void start() {

        StringsCompressor stringsCompressor = new StringsCompressor();
        StringsExpander stringsExpander = new StringsExpander();
        String text = "";

        System.out.println("課題 1. 文字列圧縮");

        System.out.println("実行例 1");
        text = stringsCompressor.encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
        System.out.println(text);
        System.out.println("A5B10CD9E2F5G(答え)");

        System.out.println("実行例 2");
        text = stringsCompressor.encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFGG");
        System.out.println(text);
        System.out.println("A5B10CD9E2F5G2(答え)");

        System.out.println("");

        System.out.println("課題 2. 文字列展開");
        System.out.println("実行例 1");
        text = stringsExpander.decode("A5B10CD9E2F5G");
        System.out.println(text);
        System.out.println("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG(答え)");

        System.out.println("実行例 2");
        text = stringsExpander.decode("A5B10CD9E2F5G2");
        System.out.println(text);
        System.out.println("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFGG(答え)");

    }
}