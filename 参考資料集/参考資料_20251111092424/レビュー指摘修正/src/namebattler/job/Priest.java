package namebattler.job;

import java.util.ArrayList;
import java.util.Random;

import namebattler.spell.*;

/**
 * プレイヤーを継承した僧侶のクラス
 */
public class Priest extends Player {
    private static final int MIN_HP = 80;
    private static final int HP_RANGE = 120;
    private static final int MIN_MP = 20;
    private static final int MP_RANGE = 30;
    private static final int MIN_STR = 10;
    private static final int STR_RANGE = 60;
    private static final int MIN_DEF = 10;
    private static final int DEF_RANGE = 60;
    private static final int MIN_LUCK = 1;
    private static final int LUCK_RANGE = 99;
    private static final int MIN_AGI = 20;
    private static final int AGI_RANGE = 40;

    Random rand = new Random();

    /**
     * コンストラクタ
     * 僧侶のステータス範囲はそれぞれHP:80～200 MP:20～50 STR:10～70 DEF:10～70 LUCK:1～100 AGI:20～60
     * 親クラスのコンストラクタで計算されたステータスを僧侶のステータス範囲に応じて変換する。
     * 
     * @param name 名前
     */
    public Priest(String name) {
        super(name);
        this.jobName = "僧侶";
        // [必須] 各職業毎のステータス設定処理を共通化してみましょう！
        // 各職業で変わるのは「各パラメータのMIN値」と「各パラメータのRANGE値」だけなので、パラメータの初期化処理(計算含み)は共通化出来ます
        this.hp = MIN_HP + getHp() * HP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.maxHp = getHp();
        this.mp = MIN_MP + getMp() * MP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.str = MIN_STR + getStr() * STR_RANGE / SUPER_CLASS_MAX_STATUS;
        this.def = MIN_DEF + getDef() * DEF_RANGE / SUPER_CLASS_MAX_STATUS;
        this.luck = MIN_LUCK + getLuck() * LUCK_RANGE / SUPER_CLASS_MAX_STATUS;
        this.agi = MIN_AGI + getAgi() * AGI_RANGE / SUPER_CLASS_MAX_STATUS;

        this.spellList = new ArrayList<>();
        spellList.add(new ParalyzeSpell());
        spellList.add(new PoisonSpell());
        spellList.add(new Heal());
    }

    /**
     * HPが半分以下の場合は最優先で回復呪文を唱える。
     * そうでないなら状態異常呪文を無作為に選択し、MPが足りていれば唱える。そうでない場合は通常攻撃を行う。
     * 
     * @param target 呪文対象のプレイヤー
     */
    @Override
    public void attack(Player target) {
        if (getHp() <= getMaxHp() / 2) {
            Spell heal = spellList.get(spellList.size() - 1);
            if (canSpendMp(heal.getMpCost())) {
                castSpell(heal, this);
                return;
            }
        }
        int index = rand.nextInt(spellList.size() - 1);
        Spell abnormalStateSpell = getSpellList().get(index);
        if (canSpendMp(abnormalStateSpell.getMpCost())) {
            castSpell(abnormalStateSpell, target);
        } else {
            normalAttack(target);
        }
    }
}
