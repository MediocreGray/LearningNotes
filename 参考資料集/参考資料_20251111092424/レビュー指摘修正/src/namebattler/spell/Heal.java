package namebattler.spell;

import namebattler.job.Player;

/**
 * 呪文：ヒールを管理するクラス
 */
public class Heal extends Spell {
    private static final String SPELL_NAME = "ヒール";
    private static final int MP_COST = 20;
    private static final int HEAL_AMOUNT = 50;

    /**
     * コンストラクタ
     */
    public Heal() {
        this.name = SPELL_NAME;
        this.mpCost = MP_COST;
    }

    /**
     * ヒールを発動する
     * MPを支払い、HPを回復する
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    @Override
    public void activate(Player caster, Player target) {
        target.gainHp(HEAL_AMOUNT);
    }
}
