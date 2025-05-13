package StringCompression;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StringsCompressor {
    /**
     * 文字列を圧縮する
     * 
     * @param orgString 元の文字列
     * @return 圧縮後の文字列
     */
    public String encode(String orgString) {

        // 文字がない場合。早期リターン
        if (orgString.length() <= 0) {
            return "";
        }

        char[] charArray = orgString.toCharArray();
        String compressedString = "";
        int charCount = 0;

        char beforeChar = charArray[0];

        for (char targetChar : charArray) {
            if (beforeChar == targetChar) {
                // 前の文字と同じ場合、カウントアップ
                charCount++;

            } else {
                // 前の文字と違う場合、圧縮文字を追加
                compressedString += GetComressString(charCount,beforeChar);

                // 文字に相違があったので、現在の文字が前の文字となる。
                beforeChar = targetChar;
                charCount = 1;
            }
        }

        // 最後の圧縮文字を入れる
        compressedString += GetComressString(charCount,beforeChar);

        return compressedString;
    }

    /**
     * 圧縮した文字列の取得
     * 
     * @param count 文字が続いた回数
     * @param getChar 取得する文字
     * @return 圧縮文字列
     */
    private String GetComressString(int count, char getChar)
    {
        if (count == 1) {
            // 文字が連続しなかった場合、数値は入れない。
            return String.valueOf(getChar);

        } else {
            // 文字が連続した場合、「文字＋数」を圧縮文字列に追加
            return String.valueOf(getChar) + count;

        }
    }
}