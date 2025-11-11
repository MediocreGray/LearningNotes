// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.job;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import namebattler.abnormalstatus.AbnormalStatus;
import namebattler.spell.Spell;

// [必須] ハードコーディング(マジックナンバー)は定数化しましょう
/**
 * プレイヤーの基底クラス
 */
public abstract class Player {
    public static final int JOB_TYPE_COUNT = 4;
    private static final String STATUS_STRING = "名前:%s\n職業:%s\nHP:    %3d\tMP:    %3d\n攻撃力:%3d\t防御力:%3d\n運:    %3d\t素早さ:%3d\n\n";
    private static final String GAIN_HP_MESSAGE = "%s のキズが かいふくした!\n";
    private static final String ZERO_DAMAGE_MESSAGE = "ミス! ダメージをうけない!\n";
    private static final String TAKE_DAMAGE_MESSAGE = "%s に %dのダメージ!\n";
    private static final String PLAYER_DEATH_MESSAGE = "%s は ちからつきた...\n";
    private static final String ATTACK_MESSAGE = "%s のこうげき!\n";
    private static final int CRITICAL_RAND_RANGE = 1000;
    private static final String CRITICAL_ATTACK_MESSAGE = "かいしんのいちげき!\n";
    private static final String CAST_SPELL_MESSAGE = "%s は %s をとなえた!\n";

    public static final int SUPER_CLASS_MAX_STATUS = 255;

    // [必須] こちら元々はprotectedですが、publicに変更されています。変更した意図は何でしょうか？
    // どこからでも操作出来てしまうと「カプセル化」の概念が適用されなくなり、変更箇所や影響箇所が測りにくくなってしまいます。
    // PlayerもしくはPlayerの子クラス以外から各パラメータを操作する必要はないはずなので、protectedに戻しましょう
    /** プレイヤー名 */
    public String name;
    /** ジョブ名 */
    public String jobName;
    /** プレイヤーのHP */
    public int hp;
    /** プレイヤーの最大HP */
    public int maxHp;
    /** プレイヤーのMP */
    public int mp;
    /** プレイヤーの攻撃力 */
    public int str;
    /** プレイヤーの防御力 */
    public int def;
    /** プレイヤーの運 */
    public int luck;
    /** プレイヤーの素早さ */
    public int agi;
    /** 所持呪文リスト */
    public List<Spell> spellList;
    /** 所持状態異常リスト */
    public List<Class<? extends AbnormalStatus>> abnormalStatusList;

    /**
     * プレイヤーのコンストラクタ
     */
    public Player(String name) {
        this.name = name;
        // 何番目の数値を取り出すかは名前の文字数によって決める事にする
        // ハッシュ値から取り出した数値は20種類であるため引数には文字数を20で割った余りを使用する
        this.hp = generateNumber(name, name.length() % 20);
        this.mp = generateNumber(name, (name.length() + 1) % 20);
        this.str = generateNumber(name, (name.length() + 2) % 20);
        this.def = generateNumber(name, (name.length() + 3) % 20);
        this.luck = generateNumber(name, (name.length() + 4) % 20);
        this.agi = generateNumber(name, (name.length() + 5) % 20);
        this.spellList = new ArrayList<>();
        this.abnormalStatusList = new ArrayList<>();
    }

    // [必須] ハッシュダイジェストから数値を取り出す処理はプレイヤークラスに依存する処理ではないので、
    // 初級編を参考にして、別のクラスに切り出しましょう。
    /**
     * ハッシュダイジェストから数値を取り出す
     * 
     * @param name  名前
     * @param index 何番目の数値を取り出すか
     * @return 数値(0 ～ 255)
     */
    public static int generateNumber(String name, int index) {
        try {
            String digest = getHashDigest(name);
            String hex = digest.substring(
                    index * 2, index * 2 + 2);

            return Integer.parseInt(hex, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * ハッシュダイジェストを取得
     * 
     * @param name 名前
     * @return ハッシュダイジェスト
     */
    public static String getHashDigest(String name) {
        try {
            // ハッシュ値を取得する
            byte[] result = MessageDigest.getInstance("SHA-1")
                    .digest(name.getBytes());
            return String.format(
                    "%040x",
                    new BigInteger(1, result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * プレイヤー名を取得
     * 
     * @return プレイヤー名
     */
    public String getName() {
        return name;
    }

    /**
     * ジョブ名を取得
     * 
     * @return ジョブ名を返す
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * HPを取得
     * 
     * @return HPを返す
     */
    public int getHp() {
        return hp;
    }

    /**
     * 最大HPを取得
     * 
     * @return 最大HPを返す
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * MPを取得
     * 
     * @return MPを返す
     */
    public int getMp() {
        return mp;
    }

    /**
     * 攻撃力を取得
     * 
     * @return 攻撃力を返す
     */
    public int getStr() {
        return str;
    }

    /**
     * 防御力を取得
     * 
     * @return 防御力を返す
     */
    public int getDef() {
        return def;
    }

    /**
     * 運を取得
     * 
     * @return 運を返す
     */
    public int getLuck() {
        return luck;
    }

    /**
     * 素早さを取得
     * 
     * @return 素早さを返す
     */
    public int getAgi() {
        return agi;
    }

    /**
     * 所持呪文リストを取得
     * 
     * @return 所持呪文リストを返す
     */
    public List<Spell> getSpellList() {
        return spellList;
    }

    /**
     * 所持状態異常リストを取得
     * 
     * @return 所持状態異常リストを返す
     */
    public List<Class<? extends AbnormalStatus>> getAbnormalStatusList() {
        return abnormalStatusList;
    }

    /**
     * ステータスを表示する
     */
    public void showStatus() {
        System.out.printf(STATUS_STRING,
                getName(),
                getJobName(),
                getHp(),
                getMp(),
                getStr(),
                getDef(),
                getLuck(),
                getAgi());
    }

    /**
     * 死亡判定を行う
     * 
     * @return HPが0以下ならtrue、そうでないならfalse
     */
    public boolean isDead() {
        return getHp() <= 0;
    }

    /**
     * MPを支払えるかどうか
     * 
     * @param mpCost MPコスト
     * @return 支払えるならtrue,そうでないならfalse
     */
    public boolean canSpendMp(int mpCost) {
        if (getMp() >= mpCost) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ある状態異常に掛かっているかどうか
     * 
     * @param statusType 調べたい状態異常
     * @return 掛かっていればtrue、そうでないならfalse
     */
    public boolean hasAbnormalStatusOf(Class<? extends AbnormalStatus> statusType) {
        for (Class<? extends AbnormalStatus> abnormalStatus : getAbnormalStatusList()) {
            if (statusType.equals(abnormalStatus)) {
                return true;
            }
        }
        return false;
    }

    /**
     * HPを回復する
     * 最大HPを上回る場合は最大HPと同じ数値に修正する
     * 
     * @param amount 回復量
     */
    public void gainHp(int amount) {
        this.hp += amount;
        this.hp = Math.min(getMaxHp(), getHp());
        System.out.printf(GAIN_HP_MESSAGE, getName());
    }

    /**
     * ダメージを受けてHPが減少する
     * HPが0を下回る場合は0に修正する
     * 
     * @param damage ダメージ
     */
    public void takeDamage(int damage) {
        if (damage <= 0) {
            System.out.printf(ZERO_DAMAGE_MESSAGE);
        } else {
            System.out.printf(TAKE_DAMAGE_MESSAGE, getName(), damage);
            this.hp -= damage;
            this.hp = Math.max(0, getHp());
            if (isDead()) {
                System.out.printf(PLAYER_DEATH_MESSAGE, getName());
            }
        }
    }

    /**
     * 通常攻撃を行う
     * 
     * @param target 攻撃対象のプレイヤー
     */
    public void normalAttack(Player target) {
        System.out.printf(ATTACK_MESSAGE, getName());
        int damage = Math.max(getStr() - target.getDef(), 0);
        Random rand = new Random();
        if (rand.nextInt(CRITICAL_RAND_RANGE) < getLuck()) {
            damage = getStr();
            System.out.printf(CRITICAL_ATTACK_MESSAGE);
        }
        target.takeDamage(damage);
    }

    /**
     * 呪文を唱える
     * 
     * @param spell  唱える呪文
     * @param target 呪文の対象
     */
    public void castSpell(Spell spell, Player target) {
        System.out.printf(CAST_SPELL_MESSAGE, getName(), spell.getName());
        this.mp -= spell.getMpCost();
        spell.activate(this, target);
    }

    /**
     * 攻撃を行う抽象メソッド
     * 
     * @param target 攻撃対象のプレイヤー
     */
    public abstract void attack(Player target);
}