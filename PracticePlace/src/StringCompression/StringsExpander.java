package StringCompression;

public class StringsExpander {
    /**
     * 圧縮された文字列を元に戻す
     * 
     * @param compressedString 圧縮された文字列
     * @return 展開後の文字列
     */
    public String decode(String compressedString) {

        // 文字がない場合、処理終了
        if (compressedString == null || compressedString.isEmpty()) {
            return "";
        }

        char[] charArray = compressedString.toCharArray();
        StringBuilder expandedString = new StringBuilder();

        // 一文字づつ解析
        int i = 0;
        while (i < charArray.length) {

            // 文字を取得
            char targetChar = charArray[i];
            // 次の文字に進む
            i++;
            // 数値を取得
            String numberStr = "";
            while (i < charArray.length && Character.isDigit(charArray[i])) {
                numberStr += charArray[i];
                i++; // 数字を取得したら次の文字に進む
            }

            if (numberStr.isEmpty()) {
                // 数字がない場合、一文字だけ展開後の文字列に追加
                expandedString.append(targetChar);
            } else {
                // 数字がある場合、その分だけ展開後の文字列に追加
                int count = Integer.parseInt(numberStr);
                for (int j = 0; j < count; j++) {
                    expandedString.append(targetChar);
                }
                numberStr = ""; // 数字をリセット
            }
        }

        return expandedString.toString();
    }
}
