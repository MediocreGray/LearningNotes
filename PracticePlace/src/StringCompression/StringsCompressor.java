package StringCompression;
    //TODO:↑全部小文字


//クラスのJavaDogが無い
public class StringsCompressor {

    // TODO:スペルorgじゃないぞ
    /**
     * 文字列を圧縮する
     * 
     * @param orginalString 元の文字列
     * @return 圧縮後の文字列
     */
    public String encode(String orginalString) {

        // 文字がない場合、処理終了
        if (orginalString == null || orginalString.isEmpty()) {
            return "";
        }

        char[] charArray = orginalString.toCharArray();
        StringBuilder compressed = new StringBuilder();
        int charCount = 0;

        char beforeChar = charArray[0];

        for (char targetChar : charArray) {
            if (beforeChar == targetChar) {
                // 前の文字と同じ場合、カウントアップ
                charCount++;

            } else {
                // 前の文字と違う場合、圧縮文字を追加
                compressed.append(getCompressString(charCount, beforeChar));

                // 文字に相違があったので、現在の文字が前の文字となる。
                beforeChar = targetChar;
                charCount = 1;
                //TODO：↑なんの１？初期値を０か１に統一か定義を追加する
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