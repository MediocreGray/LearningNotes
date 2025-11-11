package stringcompression;

/**
 * 文字列を圧縮するクラス
 */
public class StringsCompressor {

    // 連続カウントの初期値
    private static final int INITIAL_CHAR_COUNT = 1;

    /**
     * 文字列を圧縮する
     * 
     * @param originalString 元の文字列
     * @return 圧縮後の文字列
     */
    public String encode(String originalString) {

        // 文字がない場合、処理終了
        if (originalString == null || originalString.isEmpty()) {
            return "";
        }

        char[] charArray = originalString.toCharArray();
        StringBuilder compressed = new StringBuilder();

        // 先頭文字を基準にカウントは「最初の出現分を含む」1で開始
        int charCount = INITIAL_CHAR_COUNT;
        char beforeChar = charArray[0];

        // 2文字目以降から開始
        for (int i = 1; i < charArray.length; i++) {
            char targetChar = charArray[i];

            if (beforeChar == targetChar) {
                // 前の文字と同じ場合、カウントアップ
                charCount++;
            } else {
                // 前の文字と違う場合、圧縮文字を追加
                compressed.append(getCompressString(charCount, beforeChar));
                // 現在の文字を基準に戻し、カウントを初期化
                beforeChar = targetChar;
                charCount = INITIAL_CHAR_COUNT;
            }
        }

        // 最後の圧縮文字を入れる
        compressed.append(getCompressString(charCount, beforeChar));

        return compressed.toString();
    }

    /**
     * 圧縮した文字列の取得
     * 
     * @param count 文字が続いた回数
     * @param ch    文字
     * @return 圧縮文字列
     */
    private String getCompressString(int count, char ch) {
        if (count == 1) {
            // 文字が連続しなかった場合、数値は入れない。
            return String.valueOf(ch);

        } else {
            // 文字が連続した場合、「文字＋数」を圧縮文字列に追加
            return String.valueOf(ch) + count;

        }
    }
}