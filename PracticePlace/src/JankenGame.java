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

        JankenShape(String dispStr) {
            this.dispStr = dispStr;
        }

        public String getDispStr() {
            return this.dispStr;
        }
    };

    enum JankenResult {
        Win, Lose, Draw
    };

    boolean isPlayerWin;

    public static void main(String[] args) {
        JankenGame jankenGame = new JankenGame();
        jankenGame.start();
    }

    private void start() {

        printf(MSG_TITLECALL);
        printf(MSG_DESCRIPTION);
        this.showJankenShapeChoices();
        printf(NEWLINE);

        this.jankenLoop(true);

        this.showJankenResult();
    }

    void printf(String str, Object... args) {
        System.out.printf(str, args);
    }

    private void showJankenResult() {
        if (this.isPlayerWin) {
            printf(MSG_YOU_WIN);
        } else {
            printf(MSG_YOU_LOSE);
        }
    }

    private void jankenLoop(boolean isFirst) {

        if (isFirst) {
            printf(MSG_SHOUT_FIRST);
        } else {
            printf(MSG_SHOUT_DRAW);
        }

        var computerSelectedJankenShape = this.randomSelectJankenShape();
        var playerSelectedJankenShape = this.inputJankenShape();
        printf(NEWLINE);
        printf(MSG_BUTTLE_DETAIL, computerSelectedJankenShape.getDispStr(),
                playerSelectedJankenShape.getDispStr());

        JankenResult playerJankenResult = this.getPlayerJankenResult(computerSelectedJankenShape,
                playerSelectedJankenShape);

        this.setIsPlayerWin(playerJankenResult);
    }

    private void setIsPlayerWin(JankenGame.JankenResult playerJankenResult) {
        switch (playerJankenResult) {
            case Draw:
                printf(MSG_YOU_DRAW);
                this.jankenLoop(false);
                break;
            case Win:
                isPlayerWin = true;
                break;
            case Lose:
                isPlayerWin = false;
                break;
            default:
                break;
        }
    }

    private JankenResult getPlayerJankenResult(JankenShape computerSelectedJankenShape,
            JankenShape playerSelectedJankenShape) {
        switch (playerSelectedJankenShape) {
            case Goo:
                return this.getGooJankenResult(computerSelectedJankenShape);
            case Choki:
                return this.getChokiJankenResult(computerSelectedJankenShape);
            case Par:
                return this.getParJankenResult(computerSelectedJankenShape);
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
        return JankenShape.Par;
    }
}
