package namebattler.spell;

import namebattler.abnormalstatus.PoisonStatus;
import namebattler.job.Player;

/**
 * 呪文：ポイズンを管理するクラス
 */
public class PoisonSpell extends Spell {
    private static final String SPELL_NAME = "ポイズン";
    private static final int MP_COST = 10;
    private static final String SET_POISONED_MESSAGE = "%s は どくに おかされた!\n";

    /**
     * コンストラクタ
     */
    public PoisonSpell() {
        this.name = SPELL_NAME;
        this.mpCost = MP_COST;
    }

    /**
     * ポイズンを発動する
     * MPを支払い、相手を毒状態にする
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    @Override
    public void activate(Player caster, Player target) {
        if (!target.hasAbnormalStatusOf(PoisonStatus.class)) {
            target.getAbnormalStatusList().add(PoisonStatus.class);
        }
        System.out.printf(SET_POISONED_MESSAGE, target.getName());
    }
}
