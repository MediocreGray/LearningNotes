// ★コードレビューのコメントの分類・意味
//   必須 → 「必ず実施してほしい」という意味
//   意見 → In My Opinionの略で「私的には～した方が良い」という意味
//   細かい → Nitpickの略 「細かいことだけど…」という意味
//   FIX → 指摘が修正出来ている
//   参考 → For Your Informationの略 「参考までに」という意味
//   参考：https://qiita.com/kamihork/items/be0d7bdad8ae5a8082fb
package namebattler.abnormalstatus;

/**
 * 状態異常の基底クラス
 */
public class AbnormalStatus {
    /** 状態異常名 */
    protected String statusName;

    // [細かい] 今のコードで動作はするのですが、現状だと各状態異常の処理が静的メソッドで個別に実装されています
    // そのため、新しい状態異常を追加するたびにPlayerクラスやNameBattlerクラスを修正する必要があります
    // AbnormalStatusクラスの設計を少し変更すると、この辺りを簡単に記載出来るようになります
    // →/sample にいくつかサンプルコードを置いておきましたので、参考にしてみてください
}
