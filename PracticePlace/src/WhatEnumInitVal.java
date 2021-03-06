
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
