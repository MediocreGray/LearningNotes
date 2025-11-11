package namebattler.job;

/**
 * プレイヤーを継承した戦士のクラス
 */
public class Fighter extends Player {
    private static final int MIN_HP = 100;
    private static final int HP_RANGE = 200;
    private static final int MP = 0;
    private static final int MIN_STR = 30;
    private static final int STR_RANGE = 70;
    private static final int MIN_DEF = 30;
    private static final int DEF_RANGE = 70;
    private static final int MIN_LUCK = 1;
    private static final int LUCK_RANGE = 99;
    private static final int MIN_AGI = 1;
    private static final int AGI_RANGE = 49;

    /**
     * コンストラクタ
     * 戦士のステータス範囲はそれぞれHP:100～300 MP:0 STR:30～100 DEF:30～100 LUCK:1～100 AGI:1～50
     * 親クラスのコンストラクタで計算されたステータスを戦士のステータス範囲に応じて変換する。
     * 
     * @param name 名前
     */
    public Fighter(String name) {
        super(name);
        this.jobName = "戦士";
        this.hp = MIN_HP + getHp() * HP_RANGE / SUPER_CLASS_MAX_STATUS;
        this.maxHp = getHp();
        this.mp = MP;
        this.str = MIN_STR + getStr() * STR_RANGE / SUPER_CLASS_MAX_STATUS;
        this.def = MIN_DEF + getDef() * DEF_RANGE / SUPER_CLASS_MAX_STATUS;
        this.luck = MIN_LUCK + getLuck() * LUCK_RANGE / SUPER_CLASS_MAX_STATUS;
        this.agi = MIN_AGI + getAgi() * AGI_RANGE / SUPER_CLASS_MAX_STATUS;
    }

    /**
     * 常に通常攻撃を行う
     * 
     * @param target 攻撃対象のプレイヤー
     */
    @Override
    public void attack(Player target) {
        normalAttack(target);
    }
}