
public enum WhatEnumInitVal {
    YOU_WIN("You Win"), YOU_LOSE("You Lose"), DRAW("Draw"),;

    // フィールド変数～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～
    private String resultMessage;

    // コンストラクタ～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～～
    WhatEnumInitVal(String result) {
        this.resultMessage = result;
    }

    public String getResult() {
        return this.resultMessage;
    }
}

/*
 * ■Enumの数値を取得する → jankenShape.ordinal()
 * 
 * ■Strからenum → Season.valueOf("SPRING")
 * 
 * ■Javaは列挙順に固定で「0,1,2…」ってつくっぽい
 * 
 */
