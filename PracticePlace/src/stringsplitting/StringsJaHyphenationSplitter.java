package stringsplitting;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字列固定長分割クラス
 */
public class StringsJaHyphenationSplitter {

    // 行頭禁則文字
    private static final char[] NO_FIRST_LINE_CHARS = { '、', '。' };

    /**
     * 改行コードと文字列を指定した長さで分割する
     * ※行頭禁則（行の先頭に表示されないようにする）文字を句読点（、。）とする
     * 
     * @param originalString 元の文字列
     * @param length         分割する長さ
     * @return 分割後の文字列リスト
     */
    public List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String originalString, int length) {

        List<String> splittedStrings = new ArrayList<>();
        // 長さが0の場合は元の文字列をそのまま返す
        if (0 == length) {
            splittedStrings.add(originalString);
            return splittedStrings;
        }

        // 改行コードがある場合、改行コードで分割
        StringsSplitter stringsSplitter = new StringsSplitter();
        List<String> lines = stringsSplitter.splitWithLineBreakCode(originalString);

        // 各行ごとに固定長で分割を実施
        for (String line : lines) {
            List<String> splitLines = this.splitFixedLengthJaHyphenation(line, length);
            splittedStrings.addAll(splitLines);
        }

        return splittedStrings;
    }

    /**
     * 文字列を指定した長さで分割する(サブメソッド)
     * ※行頭禁則（行の先頭に表示されないようにする）文字を句読点（、。）とする
     * 
     * @param line   分割対象の文字列
     * @param length 分割する長さ
     * @return 分割後の文字列リスト
     */
    private List<String> splitFixedLengthJaHyphenation(String line, int length) {
        List<String> splittedStrings = new ArrayList<>();
        int strLen = line.length();

        int i = 0;
        while (i < strLen) {
            int end = Math.min(i + length, strLen);

            // 分割位置の次の文字が存在し、かつ行頭禁則文字なら1文字多く取る
            if (end == i + length && end < strLen) {
                char nextChar = line.charAt(end);
                if (isNoFirstLineChar(nextChar)) {
                    end = Math.min(end + 1, strLen);
                }
            }

            // 切り出し
            splittedStrings.add(line.substring(i, end));

            // 次の開始位置は直前のendとする
            i = end;
        }

        return splittedStrings;
    }

    /**
     * 行頭禁則文字かどうかを判定する
     * 
     * @param c 判定する文字
     * @return 行頭禁則文字の場合はtrue、そうでない場合はfalse
     */
    private boolean isNoFirstLineChar(char c) {
        for (char noFirstlineChar : NO_FIRST_LINE_CHARS) {
            if (c == noFirstlineChar) {
                return true;
            }
        }
        return false;
    }
}