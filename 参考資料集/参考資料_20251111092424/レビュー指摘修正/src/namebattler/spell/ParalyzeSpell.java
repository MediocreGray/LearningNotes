package namebattler.spell;

import namebattler.abnormalstatus.ParalyzeStatus;
import namebattler.job.Player;

/**
 * 呪文：パライズを管理するクラス
 */
public class ParalyzeSpell extends Spell {
    private static final String SPELL_NAME = "パライズ";
    private static final int MP_COST = 10;
    private static final String SET_PARALYZED_MESSAGE = "%s は からだがしびれて うごけなくなった!\n";

    /**
     * コンストラクタ
     */
    public ParalyzeSpell() {
        this.name = SPELL_NAME;
        this.mpCost = MP_COST;
    }

    /**
     * パライズを発動する
     * MPを支払い、相手を麻痺状態にする
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    @Override
    public void activate(Player caster, Player target) {
        if (!target.hasAbnormalStatusOf(ParalyzeStatus.class)) {
            target.getAbnormalStatusList().add(ParalyzeStatus.class);
        }
        System.out.printf(SET_PARALYZED_MESSAGE, target.getName());
    }
}
