// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler;

import java.util.Random;
import java.util.Scanner;

import namebattler.abnormalstatus.*;
import namebattler.job.*;

// [必須] 各クラスで指摘しているとは思うのですが、複数クラスで共通している観点をまとめて記載します！
// １．JavaDocコメントを追加していきましょう
//    JavaDocコメントが必要な項目
//    ・クラス定義
//    ・フィールド（メンバ変数、クラス変数、定数）
//    ・メソッド定義
//    概要と書き方：https://style.potepan.com/articles/15252.html
//    ガッツリ知りたい人向け：https://www.issoh.co.jp/tech/details/3290
// ２．マジックナンバーは定数化しましょう。
//    参考：https://vertys.net/java-magic-number/
// ３．メソッド、変数などのアクセス修飾子の設定の仕方を考えましょう。本当に必要な場合だけprotectやpublicにしましょう。
// ４．変数のスコープを意識して、必要に応じてメンバー変数を定義しましょう
// ５．同じような処理が何度も繰り返される場合には、共通化を考えましょう

/**
 * ネームバトラーの実行クラス
 */
public class NameBattler {
    private static final String INPUT_PLAYER_NAME_MESSAGE = "プレイヤーの名前を入力してください:\n";
    private static final String START_BATTLE_STRING = "=== BATTLE START ===\n\n";
    private static final String TURN_START_STRING = "== TURN %d ==\n\n";
    private static final String WINNER_MESSAGE = "%sの勝利!!\n\n";

    // [必須] クラス内でしか使用しない変数かと思うので、private化しましょう
    // [必須] scanner.close()を忘れずに実行しましょう
    Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        NameBattler nameBattler = new NameBattler();

        // プレイヤー名を入力してキャラクターを生成し、ステータスを表示する。
        Player player1 = nameBattler.createCharacter();
        player1.showStatus();

        Player player2 = nameBattler.createCharacter();
        player2.showStatus();

        // バトル開始の表示
        System.out.printf(START_BATTLE_STRING);

        // 戦闘を行い勝者を決める
        Player winner = nameBattler.battleAndReturnWinner(player1, player2);

        // 勝ち負けの表示
        System.out.printf(WINNER_MESSAGE, winner.getName());
    }

    /**
     * キャラクターを生成する
     * 名前から生成したハッシュ値からジョブを決定し、そのジョブでキャラクターを生成する。
     * 
     * @return 生成したキャラクター
     */
    public Player createCharacter() {
        System.out.printf(INPUT_PLAYER_NAME_MESSAGE);
        // [細かい] 不正な引数に対する処理を追加しておきましょう。改行などを入力しても実行できてしまいます
        String name = scn.nextLine();
        int jobNumber = Player.generateNumber(name, 0) % Player.JOB_TYPE_COUNT;
        Player player = null;
        // [必須] switch文では必ずdefaultケースを用意しましょう
        // 想定外の内容が設定された時のケースなので、エラー検知出来ず省略してしまうのは良くないです
        switch (jobNumber) {
            case 0:
                // 戦士
                player = new Fighter(name);
                break;
            case 1:
                // 魔法使い
                player = new Wizard(name);
                break;
            case 2:
                // 僧侶
                player = new Priest(name);
                break;
            case 3:
                // 賢者
                player = new Sage(name);
                break;
        }
        return player;
    }

    /**
     * バトルを行い勝者を返す
     * 
     * @param player1 プレイヤー1
     * @param player2 プレイヤー2
     * @return 勝者
     */
    public Player battleAndReturnWinner(Player player1, Player player2) {
        Player winner = null;
        int turnCount = 0;
        while (winner == null) {
            turnCount++;
            System.out.printf(TURN_START_STRING, turnCount);
            player1.showStatus();
            player2.showStatus();

            // 素早さによって行動順を決定する(同値の場合はランダム)
            Player firstPlayer = null;
            Player secondPlayer = null;
            if (player1.getAgi() > player2.getAgi()) {
                firstPlayer = player1;
                secondPlayer = player2;
            } else if (player1.getAgi() < player2.getAgi()) {
                firstPlayer = player2;
                secondPlayer = player1;
            } else {
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    firstPlayer = player1;
                    secondPlayer = player2;
                } else {
                    firstPlayer = player2;
                    secondPlayer = player1;
                }
            }

            // [必須] 先攻のターンが終了した後に既に勝者が決まっている場合は、後攻のターンを実行は不要ではないでしょうか？
            // エラーなどにはならないと思いますが、単純に不要処理になっているように見えるので、見直してみましょう
            // 先攻
            executeTurn(firstPlayer, secondPlayer);
            winner = findWinner(firstPlayer, secondPlayer);

            // 後攻
            executeTurn(secondPlayer, firstPlayer);
            winner = findWinner(firstPlayer, secondPlayer);
        }
        return winner;
    }

    /**
     * ターンを進行する
     * 
     * @param activePlayer   アクティブプレイヤー
     * @param inactivePlayer 非アクティブプレイヤー
     */
    public void executeTurn(Player activePlayer, Player inactivePlayer) {
        // いずれかのプレイヤーが死亡している場合は処理を行わない。
        if (activePlayer.isDead() || inactivePlayer.isDead()) {
            return;
        }

        // 毒状態ならダメージを与える
        if (activePlayer.hasAbnormalStatusOf(PoisonStatus.class)) {
            PoisonStatus.dealDotDamageTo(activePlayer);
            if (activePlayer.isDead()) {
                return;
            }
        }
        // 麻痺状態なら確率で行動不能にする
        if (activePlayer.hasAbnormalStatusOf(ParalyzeStatus.class) && ParalyzeStatus.isIncapable(activePlayer)) {
            System.out.println();
            return;
        }
        // 攻撃する
        activePlayer.attack(inactivePlayer);
        System.out.println();
    }

    /**
     * 勝者を探して返す。いない場合はnullを返す。
     * 
     * @param player1 プレイヤー1
     * @param player2 プレイヤー2
     * @return 勝者。いなければnull。
     */
    public Player findWinner(Player player1, Player player2) {
        if (player1.isDead()) {
            return player2;
        } else if (player2.isDead()) {
            return player1;
        } else {
            return null;
        }
    }
}
