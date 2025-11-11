package stringsplitting;

import java.util.List;

/**
 * さらに文字列分割クラス
 */
public class StringsMoreSplitter {

    // 句点
    private static final String PERIOD = "。";
    // 句点と改行コード
    private static final String PERIOD_AND_BREAKCODE = "。¥n";

    /**
     * 改行コードと句点で文字列を分割し、リストに変換する
     * ※句点の直後の改行は無視する。
     * 
     * @param originalString 元の文字列
     * @return 分割後の文字列リスト
     */
    public List<String> splitWithLineBreakCodeAndPeriod(String originalString) {
        // 句点直後に改行コードがある場合、改行は無視するために置換
        String replacedStr = originalString.replace(PERIOD_AND_BREAKCODE, PERIOD);

        // 句点で改行するために、改行コードを追加
        replacedStr = replacedStr.replace(PERIOD, PERIOD_AND_BREAKCODE);

        // 改行コードで分割
        StringsSplitter splitter = new StringsSplitter();
        List<String> splittedStrings = splitter.splitWithLineBreakCode(replacedStr);

        return splittedStrings;
    }
}