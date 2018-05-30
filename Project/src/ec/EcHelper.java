package ec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
/**
 * 定数保持、処理及び表示簡略化ヘルパークラス
 *
 */
public class EcHelper {
	//検索結果
	static final String SEARCH_RESULT_PAGE = "WEB-INF/jsp/itemsearchresult.jsp";
	//商品ページ
	static final String ITEM_PAGE = "WEB-INF/jsp/item.jsp";
	//TOPページ
	static final String TOP_PAGE = "WEB-INF/jsp/index.jsp";
	//エラーページ
	static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";
	//買い物かごページ
	static final String CART_PAGE = "WEB-INF/jsp/cart.jsp";
	//購入
	static final String BUY_PAGE = "WEB-INF/jsp/buy.jsp";
	//購入確認
	static final String BUY_CONFIRM_PAGE = "WEB-INF/jsp/buyconfirm.jsp";
	//購入確認
	static final String BUY_RESULT_PAGE = "WEB-INF/jsp/buyresult.jsp";
	//ユーザーが購入したデータ一覧
	static final String USER_BUY_DATA_PAGE = "WEB-INF/jsp/userbuydata.jsp";
	//ユーザーが購入したデータ詳細
	static final String USER_BUY_HISTORY_DETAIL_PAGE = "WEB-INF/jsp/userbuyhistorydetail.jsp";
	//ユーザー一覧
	static final String USER_ALL_PAGE = "WEB-INF/jsp/userAll.jsp";
	//ユーザー情報詳細
	static final String USER_MORE_DETAIL_PAGE = "WEB-INF/jsp/userMoreDetail.jsp";
	//ユーザー情報更新
	static final String USER_UPDATE_PAGE = "WEB-INF/jsp/userUpdate.jsp";
	//ユーザー削除
	static final String USER_DELETE_PAGE = "WEB-INF/jsp/userDelete.jsp";
	//ログイン
	static final String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";
	//ユーザー新規作成
	static final String NEW_USER_PAGE = "WEB-INF/jsp/newUser.jsp";
	//ユーザーマスター画面
	static final String USER_MASTER_PAGE = "WEB-INF/jsp/usermaster.jsp";
	//製品詳細画面
	static final String PRODUCT_DETAIL_PAGE = "WEB-INF/jsp/ProductDetail.jsp";
	//製品更新画面
	static final String PRODUCT_UPDATE_PAGE = "WEB-INF/jsp/ProductUpdate.jsp";
	//製品削除画面
	static final String PRODUCT_DELETE_PAGE = "WEB-INF/jsp/ProductDelete.jsp";
	//製品の新規登録画面
	static final String NEW_PRODUCT_PAGE = "WEB-INF/jsp/NewProduct.jsp";

	public static EcHelper getInstance() {
		return new EcHelper();
	}


	/**
	 * 商品の合計金額を算出する
	 * @param items
	 * @return total
	 */
	public static int getTotalItemPrice(ArrayList<ItemDataBeans> items) {
		int total = 0;
		for(ItemDataBeans item : items) {
			total += item.getPrice();
		}
		return total;
	}
	/**
	 * ハッシュ関数
	 * @param target
	 * @return
	 */
	public static String getSha256(String target) {
		MessageDigest md = null;
		StringBuffer buf = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			byte[] digest = md.digest();

			for(int i = 0; i < digest.length; i++) {
				buf.append(String.format("%02x", digest[i]));
			}
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * セッションから指定データを取得（一緒に削除も行う）
	 * @param session
	 * @param str
	 * @return
	 */
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object test = session.getAttribute(str);
		session.removeAttribute(str);

		return test;
	}

	/**
	 * ログインIDのバリデーション
	 * @param inputLoginId
	 * @return
	 */
	public static boolean isLoginIdValidation(String inputLoginId) {
		//英数字アンダースコア以外が入力されていたら
		if(inputLoginId.matches("[0-9a-zA-Z-_]+")) {
			return true;
		}
		return false;
	}

}
