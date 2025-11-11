package namebattler.job;

import java.util.ArrayList;
import java.util.Random;

import namebattler.spell.*;

/**
 * プレイヤーを継承した賢者のクラス
 * 消費が重いが強力な攻撃呪文と回復呪文の両方を扱える
 */
public class Sage extends Player {
    private static final int MIN_HP = 70;
    private static final int HP_RANGE = 110;
    private static final int MIN_MP = 60;
    private static final int MP_RANGE = 60;
    private static final int MIN_STR = 20;
    private static final int STR_RANGE = 40;
    private static final int MIN_DEF = 20;
    private static final int DEF_RANGE = 40;
    private static final int MIN_LUCK = 1;
    private static final int LUCK_RANGE = 99;
    private static final int MIN_AGI = 20;
    private static final int AGI_RANGE = 40;

    Random rand = new Random();

    /**
     * コンストラクタ
     * 賢者のステータス範囲はそれぞれHP:70～180 MP:60～120 STR:20～60 DEF:20～60 LUCK:1～100 AGI:20～60
     * 親クラスのコンストラクタで計算されたステータスを賢者のステータス範囲に応じて変換する。
     * 
     * @param name 名前
     */
    public Sage(String name) {
        super(name);
        this.jobName = "賢者";
        this.hp = MIN_HP + getHp() * HP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.maxHp = getHp();
        this.mp = MIN_MP + getMp() * MP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.str = MIN_STR + getStr() * STR_RANGE / SUPER_CLASS_MAX_STATUS;
        this.def = MIN_DEF + getDef() * DEF_RANGE / SUPER_CLASS_MAX_STATUS;
        this.luck = MIN_LUCK + getLuck() * LUCK_RANGE / SUPER_CLASS_MAX_STATUS;
        this.agi = MIN_AGI + getAgi() * AGI_RANGE / SUPER_CLASS_MAX_STATUS;

        this.spellList = new ArrayList<>();
        spellList.add(new MegaFire());
        spellList.add(new GigaThunder());
        spellList.add(new TeraHeal());
    }

    /**
     * HPが半分以下の場合は最優先で回復呪文を唱える。
     * そうでない場合は攻撃呪文を無作為に選択し、MPが足りているなら唱える。足りていないなら通常攻撃を行う。
     * 
     * @param target 対象のプレイヤー
     */
    @Override
    public void attack(Player target) {
        if (getHp() <= getMaxHp() / 2) {
            Spell teraHeal = spellList.get(spellList.size() - 1);
            if (canSpendMp(teraHeal.getMpCost())) {
                castSpell(teraHeal, this);
                return;
            }
        }
        int index = rand.nextInt(spellList.size() - 1);
        Spell attackSpell = getSpellList().get(index);
        if (canSpendMp(attackSpell.getMpCost())) {
            castSpell(attackSpell, target);
        } else {
            normalAttack(target);
        }
    }
}
