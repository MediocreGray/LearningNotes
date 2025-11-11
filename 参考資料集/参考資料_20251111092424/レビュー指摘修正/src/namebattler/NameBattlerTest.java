package namebattler;

import java.util.Random;

import namebattler.abnormalstatus.ParalyzeStatus;
import namebattler.job.*;
import namebattler.spell.*;

/**
 * テスト用のクラス
 * 他のクラスには影響を与えない
 * 
 */
public class NameBattlerTest {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        // 生成したキャラクターのジョブの確率とステータス範囲についてのテスト
        // NameBattlerTest.createCharacterTest();

        // 攻撃呪文のダメージ範囲テスト
        // NameBattlerTest.spellDamageTest();

        // 麻痺による行動不能の確率テスト
        // NameBattlerTest.paralyzeCount();

        // 戦闘テスト
        // NameBattler nbTest = new NameBattler();
        // Player nanashi = new PunchingBag("");
        // Player fighter = new Fighter(generate20String());
        // Player wizard = new Wizard(generate20String());
        // Player priest = new Priest(generate20String());
        // Player sage = new Sage(generate20String());

        // nbTest.battleAndReturnWinner(fighter, nanashi);
        // nbTest.battleAndReturnWinner(wizard, nanashi);
        // nbTest.battleAndReturnWinner(priest, nanashi);
        // nbTest.battleAndReturnWinner(sage, nanashi);
    }

    /**
     * 20文字以内の無作為な文字列(英数字)を返す
     * 
     * @return 生成された文字列
     */
    public static String generate20String() {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    /**
     * createCharacterのテスト用メソッド
     */
    public static void createCharacterTest() {
        final int ONE_HUNDRED_COUNT = 100;

        Player player = null;
        int fighterCount = 0;
        int wizardCount = 0;
        int priestCount = 0;
        int sageCount = 0;
        for (int i = 0; i < ONE_HUNDRED_COUNT; i++) {
            System.out.println(i);
            String name = generate20String();

            // 不自然なバラつきがないか調べる
            int jobNumber = Player.generateNumber(name, 0) % Player.JOB_TYPE_COUNT;
            switch (jobNumber) {
                case 0:
                    fighterCount++;
                    break;
                case 1:
                    wizardCount++;
                    break;
                case 2:
                    priestCount++;
                    break;
                case 3:
                    sageCount++;
                    break;
            }

            // 戦士のステータス範囲はそれぞれHP:100～300 MP:0 STR:30～100 DEF:30～100 LUCK:1～100 AGI:1～50
            player = new Fighter(name);
            if (player.getHp() < 100 || player.getHp() > 300
                    || player.getMp() != 0
                    || player.getStr() < 30 || player.getStr() > 100
                    || player.getDef() < 30 || player.getDef() > 100
                    || player.getLuck() < 1 || player.getLuck() > 100
                    || player.getAgi() < 1 || player.getAgi() > 50) {
                System.out.println("ステータスエラー：戦士");
                break;
            }

            // 魔法使いのステータス範囲はそれぞれHP:50～150 MP:30～80 STR:1～50 DEF:1～50 LUCK:1～100 AGI:20～60
            player = new Wizard(name);
            if (player.getHp() < 50 || player.getHp() > 150
                    || player.getMp() < 30 || player.getMp() > 80
                    || player.getStr() < 1 || player.getStr() > 50
                    || player.getDef() < 1 || player.getDef() > 50
                    || player.getLuck() < 1 || player.getLuck() > 100
                    || player.getAgi() < 20 || player.getAgi() > 60) {
                System.out.println("ステータスエラー：魔法使い");
                break;
            }

            // 僧侶のステータス範囲はそれぞれHP:80～200 MP:20～50 STR:10～70 DEF:10～70 LUCK:1～100 AGI:20～60
            player = new Priest(name);
            if (player.getHp() < 80 || player.getHp() > 200
                    || player.getMp() < 20 || player.getMp() > 50
                    || player.getStr() < 10 || player.getStr() > 70
                    || player.getDef() < 10 || player.getDef() > 70
                    || player.getLuck() < 1 || player.getLuck() > 100
                    || player.getAgi() < 20 || player.getAgi() > 60) {
                System.out.println("ステータスエラー：僧侶");
                break;
            }

            // 賢者のステータス範囲はそれぞれHP:70～180 MP:60～120 STR:20～60 DEF:20～60 LUCK:1～100 AGI:20～60
            player = new Sage(name);
            if (player.getHp() < 70 || player.getHp() > 180
                    || player.getMp() < 60 || player.getMp() > 120
                    || player.getStr() < 20 || player.getStr() > 60
                    || player.getDef() < 20 || player.getDef() > 60
                    || player.getLuck() < 1 || player.getLuck() > 100
                    || player.getAgi() < 20 || player.getAgi() > 60) {
                System.out.println("ステータスエラー：賢者");
                break;
            }
        }

        System.out.printf("%d回中\n", ONE_HUNDRED_COUNT);
        System.out.printf("戦士:%d回\n", fighterCount);
        System.out.printf("魔法使い:%d回\n", wizardCount);
        System.out.printf("僧侶:%d回\n", priestCount);
        System.out.printf("賢者:%d回\n", sageCount);
    }

    /**
     * 攻撃呪文クラスのacrivateメソッドのテスト用メソッド
     */
    public static void spellDamageTest() {
        Player nanashi = new PunchingBag("");
        Spell fire = new Fire();
        Spell thunder = new Thunder();
        Spell megaFire = new MegaFire();
        Spell gigaThunder = new GigaThunder();

        for (int i = 0; i < 100; i++) {
            int currentHp;
            int damage;

            currentHp = nanashi.getHp();
            fire.activate(nanashi, nanashi);
            damage = currentHp - nanashi.getHp();
            // ファイアのダメージ範囲は10～30
            if (damage < 10 || damage > 30) {
                System.out.printf("ファイアのダメージ範囲エラー\n");
                break;
            }
            nanashi.gainHp(1000);

            currentHp = nanashi.getHp();
            thunder.activate(nanashi, nanashi);
            damage = currentHp - nanashi.getHp();
            // サンダーのダメージ範囲は5～50
            if (damage < 5 || damage > 50) {
                System.out.printf("サンダーのダメージ範囲エラー\n");
                break;
            }
            nanashi.gainHp(1000);

            currentHp = nanashi.getHp();
            megaFire.activate(nanashi, nanashi);
            damage = currentHp - nanashi.getHp();
            // メガファイアのダメージ範囲は15～45
            if (damage < 15 || damage > 45) {
                System.out.printf("メガファイアのダメージ範囲エラー\n");
                break;
            }
            nanashi.gainHp(1000);

            currentHp = nanashi.getHp();
            gigaThunder.activate(nanashi, nanashi);
            damage = currentHp - nanashi.getHp();
            // ギガサンダーのダメージ範囲は10～70
            if (damage < 10 || damage > 70) {
                System.out.printf("ギガサンダーのダメージ範囲エラー\n");
                break;
            }
            nanashi.gainHp(1000);
        }
    }

    /**
     * 麻痺による行動不能確率を調べるためのメソッド
     */
    public static void paralyzeCount() {
        final int THOUSAND_COUNT = 1000;
        Player nanashi = new PunchingBag("");
        int paralyzeCount = 0;
        for (int i = 0; i < 1000; i++) {
            if (ParalyzeStatus.isIncapable(nanashi)) {
                paralyzeCount++;
            }
        }
        // 20%に収束しているはず
        System.out.printf("麻痺した回数は%d回中%d回\n", THOUSAND_COUNT, paralyzeCount);
    }

}

/**
 * サンドバッグ用のクラス
 */
class PunchingBag extends Player {

    /**
     * コンストラクタ
     * サンドバッグ用のステータスに書き換える
     * 
     * @param name 名前(コンストラクタ内で書き換えるので意味は無い)
     */
    public PunchingBag(String name) {
        super(name);
        this.name = "ナナシ";
        this.jobName = "サンドバッグ";
        this.hp = 500;
        this.maxHp = getHp();
        this.mp = 0;
        this.str = 0;
        this.def = 1;
        this.luck = 0;
        this.agi = 0;
    }

    /**
     * 1ダメージを与える以外は特に何もしない
     */
    @Override
    public void attack(Player target) {
        System.out.printf("ナナシはただボーっと突っ立っている!\nそれなのになぜか");
        target.takeDamage(1);
    }
}
