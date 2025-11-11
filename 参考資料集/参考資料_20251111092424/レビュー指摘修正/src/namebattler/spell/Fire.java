package namebattler.spell;

import java.util.Random;

import namebattler.job.Player;

/**
 * 呪文：ファイアを管理するクラス
 */
public class Fire extends Spell {
    private static final String SPELL_NAME = "ファイア";
    private static final int MP_COST = 10;
    private static final int MIN_DAMAGE = 10;
    private static final int DAMAGE_RANGE = 21;

    Random rand = new Random();

    /**
     * コンストラクタ
     */
    public Fire() {
        this.name = SPELL_NAME;
        this.mpCost = MP_COST;
    }

    /**
     * ファイアを発動する
     * MPを支払い、防御無視の乱数ダメージを与える
     * 
     * @param caster 呪文の使用者
     * @param target 呪文対象
     */
    @Override
    public void activate(Player caster, Player target) {
        int damage = MIN_DAMAGE + rand.nextInt(DAMAGE_RANGE);
        target.takeDamage(damage);
    }
}
