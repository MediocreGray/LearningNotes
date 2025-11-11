package stringsplitting;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 文字列分割クラス
 */
public class StringsSplitter {

    // 改行コード
    private static final String BREAK_CODE = "¥n";

    /**
     * 改行コードで文字列を分割し、リストに変換する
     * 
     * @param originalString 元の文字列
     * @return 分割後の文字列リスト
     */
    public List<String> splitWithLineBreakCode(String originalString) {

        // 改行コードで分割
        String[] splittedString = originalString.split(BREAK_CODE);

        return new ArrayList<>(Arrays.asList(splittedString));
    }
}