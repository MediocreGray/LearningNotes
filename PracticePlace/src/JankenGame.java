public class JankenGame {
    final String NEWLINE = "\n";
    final String MSG_TITLECALL = "じゃんけん勝負\n";
    final String MSG_DESCRIPTION = "グーチョキパーを数字で入力してね\n";
    final String MSG_BUTTLE_DETAIL = "%s(COM)と%s(Player)で…\n";

    final String MSG_SHOUT_FIRST = "最初はグー、じゃんけん：";
    final String MSG_SHOUT_DRAW = "あいこで：";

    final String MSG_YOU_DRAW = "あいこだよ！\n";
    final String MSG_YOU_WIN = "あなたの勝ち\n";
    final String MSG_YOU_LOSE = "あなたの負け\n";

    final String STR_JANKEN_GOO = "グー";
    final String STR_JANKEN_CHOKI = "チョキ";
    final String STR_JANKEN_PAR = "パー";

    enum JankenShape {
        Goo("グー"), Choki("チョキ"), Par("パー"),;

        private String dispStr;

        JankenShape(String str) {
            dispStr = str;
        }

        public String getDispStr() {
            return dispStr;
        }
    };

    enum JankenResult {
        Win, Lose, Draw
    };

    public static void main(String[] args) {
        JankenGame jankenGame = new JankenGame();
        jankenGame.start();
    }

    private void start() {

        printf(MSG_TITLECALL);
        printf(MSG_DESCRIPTION);
        showJankenShapeChoices();

        boolean isPlayerWin = jankenLoop(true);
        showJankenResult(isPlayerWin);
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }

    private void showJankenResult(boolean isPlayerWin) {
        if (isPlayerWin) {
            printf(MSG_YOU_WIN);
        } else {
            printf(MSG_YOU_LOSE);
        }
    }

    private boolean jankenLoop(boolean isFirst) {
        printf(NEWLINE);

        if (isFirst) {
            printf(MSG_SHOUT_FIRST);
        } else {
            printf(MSG_SHOUT_DRAW);
        }

        JankenShape computerSelectedJankenShape = randomSelectJankenShape();
        JankenShape playerSelectedJankenShape = inputJankenShape();
        printf(MSG_BUTTLE_DETAIL, computerSelectedJankenShape.getDispStr(),
                playerSelectedJankenShape.getDispStr());

        JankenResult playerJankenResult = getPlayerJankenResult(computerSelectedJankenShape,
                playerSelectedJankenShape);

        switch (playerJankenResult) {
            case Draw:
                printf(MSG_YOU_DRAW);
                return jankenLoop(false);

            case Win:
                return true;

            case Lose:
            default:
                return false;
        }
    }

    private JankenResult getPlayerJankenResult(JankenShape computerSelectedJankenShape,
            JankenShape playerSelectedJankenShape) {
        switch (playerSelectedJankenShape) {
            case Goo:
                return getGooJankenResult(computerSelectedJankenShape);
            case Choki:
                return getChokiJankenResult(computerSelectedJankenShape);
            case Par:
                return getParJankenResult(computerSelectedJankenShape);
            default:
                return null;
        }
    }

    private JankenResult getGooJankenResult(JankenShape computerSelectedJankenShape) {
        switch (computerSelectedJankenShape) {
            case Goo:
                return JankenResult.Draw;
            case Choki:
                return JankenResult.Win;
            case Par:
                return JankenResult.Lose;
            default:
                return null;
        }
    }

    private JankenResult getChokiJankenResult(JankenShape computerSelectedJankenShape) {
        switch (computerSelectedJankenShape) {
            case Goo:
                return JankenResult.Lose;
            case Choki:
                return JankenResult.Draw;
            case Par:
                return JankenResult.Win;
            default:
                return null;
        }
    }

    private JankenResult getParJankenResult(JankenShape computerSelectedJankenShape) {
        switch (computerSelectedJankenShape) {
            case Goo:
                return JankenResult.Win;
            case Choki:
                return JankenResult.Lose;
            case Par:
                return JankenResult.Draw;
            default:
                return null;
        }
    }

    private JankenShape randomSelectJankenShape() {
        return JankenShape.Goo;
    }

    private void showJankenShapeChoices() {
        for (JankenShape jankenShape : JankenShape.values()) {
            printf("%d:%s\n", jankenShape.ordinal(), jankenShape.getDispStr());

        }
    }

    private JankenShape inputJankenShape() {

        String inputedStr = inputStr();
        printf(inputedStr + NEWLINE);// 仮置き 入力でEnter分

        return convertStringToJankenShape(inputedStr);
    }

    private String inputStr() {
        return "1";
    }

    private JankenShape convertStringToJankenShape(String str) {

        switch (str) {
            case "0":
                return JankenShape.Goo;
            case "1":
                return JankenShape.Choki;
            case "2":
                return JankenShape.Par;
            default:
                return null; //TODO:予期しない文字列の場合
        }

    }
}
