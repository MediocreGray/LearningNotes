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
        StringBuilder expanded = new StringBuilder();

        char currentChar = '\0';
        String numberStr = "";

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];

            if (Character.isLetter(ch)) {
                // 前の文字と数字を展開する
                if (currentChar != '\0') {
                    int count = 1;
                    if (!numberStr.isEmpty()) {
                        count = Integer.parseInt(numberStr);
                    }
                    for (int j = 0; j < count; j++) {
                        expanded.append(currentChar);
                    }
                    numberStr = ""; // 数字をリセット
                }

                currentChar = ch;

            } else if (Character.isDigit(ch)) {
                numberStr += ch;
            }
        }

        // 最後の文字と数字を展開する
        if (currentChar != '\0') {
            int count = 1;
            if (!numberStr.isEmpty()) {
                count = Integer.parseInt(numberStr);
            }
            for (int j = 0; j < count; j++) {
                expanded.append(currentChar);
            }
        }

        return expanded.toString();
    }
}
