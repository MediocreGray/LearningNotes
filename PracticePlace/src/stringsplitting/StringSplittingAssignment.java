package stringsplitting;

import java.util.List;

/**
 * 文字列圧縮課題の実行クラス
 */
public class StringSplittingAssignment {

    /**
     * メインメソッド
     */
    public static void main(String[] args) {
        StringSplittingAssignment.start();
    }

    /**
     * 処理開始
     */
    private static void start() {

        StringsSplitter stringsSplitter = new StringsSplitter();
        String text = "";

        System.out.println("課題 1. 文字列圧縮");
        List<String> lines = stringsSplitter.splitWithLineBreakCode(
                "１行目。¥n２行目。¥n３行目。¥n４行目。¥n¥n５行目¥n");
                
        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("");

    }
}