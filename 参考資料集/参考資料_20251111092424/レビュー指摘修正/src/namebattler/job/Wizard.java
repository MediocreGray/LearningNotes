package namebattler.job;

import java.util.ArrayList;
import java.util.Random;

import namebattler.spell.*;

/**
 * プレイヤーを継承した魔法使いのクラス
 */
public class Wizard extends Player {
    private static final int MIN_HP = 50;
    private static final int HP_RANGE = 100;
    private static final int MIN_MP = 30;
    private static final int MP_RANGE = 50;
    private static final int MIN_STR = 1;
    private static final int STR_RANGE = 49;
    private static final int MIN_DEF = 1;
    private static final int DEF_RANGE = 49;
    private static final int MIN_LUCK = 1;
    private static final int LUCK_RANGE = 99;
    private static final int MIN_AGI = 20;
    private static final int AGI_RANGE = 40;

    Random rand = new Random();

    /**
     * コンストラクタ
     * 魔法使いのステータス範囲はそれぞれHP:50～150 MP:30～80 STR:1～50 DEF:1～50 LUCK:1～100 AGI:20～60
     * 親クラスのコンストラクタで計算されたステータスを魔法使いのステータス範囲に応じて変換する。
     * 
     * @param name 名前
     */
    public Wizard(String name) {
        super(name);
        this.jobName = "魔法使い";
        this.hp = MIN_HP + getHp() * HP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.maxHp = getHp();
        this.mp = MIN_MP + getMp() * MP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.str = MIN_STR + getStr() * STR_RANGE / SUPER_CLASS_MAX_STATUS;
        this.def = MIN_DEF + getDef() * DEF_RANGE / SUPER_CLASS_MAX_STATUS;
        this.luck = MIN_LUCK + getLuck() * LUCK_RANGE / SUPER_CLASS_MAX_STATUS;
        this.agi = MIN_AGI + getAgi() * AGI_RANGE / SUPER_CLASS_MAX_STATUS;

        this.spellList = new ArrayList<>();
        spellList.add(new Fire());
        spellList.add(new Thunder());
    }

    /**
     * 呪文を無作為に選択し、MPが足りているなら唱える。足りていないなら通常攻撃を行う。
     * 
     * @param target 攻撃対象のプレイヤー
     */
    @Override
    public void attack(Player target) {
        int index = rand.nextInt(spellList.size());
        Spell selectedSpell = getSpellList().get(index);
        if (canSpendMp(selectedSpell.getMpCost())) {
            castSpell(selectedSpell, target);
        } else {
            normalAttack(target);
        }
    }
}
