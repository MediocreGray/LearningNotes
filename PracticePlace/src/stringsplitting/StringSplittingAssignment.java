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
        StringsMoreSplitter stringsMoreSplitter = new StringsMoreSplitter();
        StringsFixedLengthSplitter stringsFixedLengthSplitter = new StringsFixedLengthSplitter();
        StringsJaHyphenationSplitter stringsJaHyphenationSplitter = new StringsJaHyphenationSplitter();

        System.out.println("課題 1. 改行コードで分割、リストに変換");

        List<String> lines = stringsSplitter.splitWithLineBreakCode(
                "１行目。¥n２行目。¥n３行目。¥n４行目。¥n¥n５行目¥n");

        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("==============================");

        System.out.println("課題 2. さらに句点でも分割");
        lines = stringsMoreSplitter.splitWithLineBreakCodeAndPeriod(
                "１行目。２行目。¥n３行目。４行目。¥n¥n５行目。");

        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("==============================");

        System.out.println("課題 3. さらに固定長で分割");
        lines = stringsFixedLengthSplitter.splitFixedLengthWithLineBreakCodeAndPeriod(
                "このプログラムは、文字列を指定された幅で改行するサンプルプログラムです。",
                6);

        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("==============================");

        System.out.println("課題 4. さらに禁則処理を追加");

        lines = stringsJaHyphenationSplitter.splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(
                "このプログラムは、句読点を行頭禁則処理するサンプル。¥n"
                        + "最後の行です",
                8);

        for (String line : lines) {
            System.out.println(line);
        }
    }
}