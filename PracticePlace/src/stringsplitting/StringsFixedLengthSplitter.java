package stringsplitting;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字列固定長分割クラス
 */
public class StringsFixedLengthSplitter {

    /**
     * 文字列を指定した長さで分割する
     * 
     * @param originalString 元の文字列
     * @param length         分割する長さ
     * @return 分割後の文字列リスト
     */
    public List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String originalString, int length) {

        List<String> splittedStrings = new ArrayList<>();

        // 長さが0の場合は元の文字列をそのまま返す
        if (0 == length) {
            splittedStrings.add(originalString);
            return splittedStrings;
        }

        // 分割する長さごとに文字列を切り出していく
        int strLen = originalString.length();
        for (int i = 0; i < strLen; i += length) {
            int end = Math.min(i + length, strLen);
            splittedStrings.add(originalString.substring(i, end));
        }

        return splittedStrings;
    }
}