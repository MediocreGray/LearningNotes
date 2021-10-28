import java.text.DecimalFormat;
import java.text.NumberFormat;

public class JankenGame {

    // #region 定数
    final String NEWLINE = "\n";
    final String MSG_TITLECALL = "じゃんけん勝負\n";
    final String MSG_DESCRIPTION = "グーチョキパーを数字で入力してね\n";
    final String MSG_SHOUT_FIRST = "最初はグー、じゃんけん：";
    final String MSG_BUTTLE_DETAIL = "%s(COM)と%s(Player)で…";

    final String STR_JANKEN_GOO = "グー";
    final String STR_JANKEN_CHOKI = "チョキ";
    final String STR_JANKEN_PAR = "パー";

    enum JankenShape {
        None, Goo, Choki, Par
    };

    enum JankenResult {
        None, Win, Lose, Draw
    };

    // #endregion

    // #region メンバ
    // #endregion

    // #region コンストラクタ
    // #endregion

    public static void main(String[] args) {
        JankenGame jankenGame = new JankenGame();
        jankenGame.Start();
    }

    private void Start() {
        System.out.printf(MSG_TITLECALL);
        System.out.printf(MSG_DESCRIPTION);
        this.showJankenShapeChoices();
        System.out.printf(NEWLINE);

        System.out.printf(MSG_SHOUT_FIRST);
        var computerSelectedJankenShape = this.randomSelectJankenShape();
        var playerSelectedJankenShape = this.inputJankenShape();
        System.out.printf(MSG_BUTTLE_DETAIL, computerSelectedJankenShape, playerSelectedJankenShape);

        var playerJankenResult = this.getPlayerJankenResult(computerSelectedJankenShape, playerSelectedJankenShape);

        // TODO:勝負結果が出たので、勝敗とループさせていこうか
    }

    private JankenResult getPlayerJankenResult(JankenShape computerSelectedJankenShape,
            JankenShape playerSelectedJankenShape) {
        switch (playerSelectedJankenShape) {
            case Goo:
                return this.getGooJankenResult(computerSelectedJankenShape);
            case Choki:
                return this.getParJankenResult(computerSelectedJankenShape);
            case Par:
                return this.getGooJankenResult(computerSelectedJankenShape);
            default:
                return JankenResult.None;
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
                return JankenResult.None;
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
                return JankenResult.None;
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
                return JankenResult.None;
        }
    }

    private JankenShape randomSelectJankenShape() {
        return JankenShape.Goo;
    }

    private void showJankenShapeChoices() {
        // 0：グー
        // 1：チョキ
        // 2：パー
        System.out.printf("０ぐー　１ちょき　２ぱー\n");
    }

    private JankenShape inputJankenShape() {
        return JankenShape.Goo;
    }
}
