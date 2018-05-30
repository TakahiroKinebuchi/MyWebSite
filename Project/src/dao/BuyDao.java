package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDataBeans;

public class BuyDao {
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static BuyDao getInstance() {
		return new BuyDao();
	}

	/**
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy(user_id, total_price, delivery_method_id, create_date) VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setInt(3, bdb.getDeliveryMethodId());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()) {
				autoIncKey = rs.getInt(1);
			}
			System.out.println("inserting buy-datas has been completed");

			return autoIncKey;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
			}
		}
	}

	/**
	 * 削除を行う
	 * userIdに紐づくt_buyテーブルの情報をsqlから消去する
	 */
	public void DeleteBuyInfo(int userId) {
		Connection conn = null;
		try {
			//DBへ接続
			conn = DBManager.getConnection();
			//DELETE文を準備
			String sql = "DELETE FROM t_buy WHERE user_id=?";
			//sqlの?に引数をセットする
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			//sql実行
			int result = pStmt.executeUpdate();
			//追加された行数を出力
			System.out.println(result);
			pStmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return ArrayList<BuyDataBeans>
	 * 					購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static ArrayList<BuyDataBeans> getBuyDataBeansByBuyId(int buyId) throws SQLException{
		Connection con = null;
		ArrayList<BuyDataBeans> buyList = new ArrayList<BuyDataBeans>();
		try {
			//DBに接続
			con = DBManager.getConnection();
			//SELECT文を準備
			String sql = "SELECT * FROM t_buy"
					+ " JOIN m_delivery_method"
					+ " ON t_buy.delivery_method_id = m_delivery_method.id"
					+ " WHERE t_buy.id = " + buyId;
			//Statementを宣言する
			Statement st = con.createStatement();
			//SELECTを実行し、結果表を取得
			ResultSet rs = st.executeQuery(sql);

			//sqlをコンソールに出力
			System.out.println(sql);
			//結果表に格納された内容をBuyDataBeansインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				BuyDataBeans bdb = new BuyDataBeans();

				bdb.setId(rs.getInt("id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDeliveryMethodId(rs.getInt("delivery_method_id"));
				bdb.setDeliveryMethodName(rs.getString("name"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));

				buyList.add(bdb);
			}
			System.out.println(buyList);
			System.out.println("searching BuyDataBeans by buyID has been completed");

			return buyList;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
			}
		}
	}
	/**
	 * ユーザIDによる購入情報検索
	 * @param userId
	 * @return ArrayList<BuyDataBeans>
	 * 					購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static ArrayList<BuyDataBeans> getBuyDataBeansByUserId(int userId) throws SQLException{
		Connection con = null;
		ArrayList<BuyDataBeans> buyList = new ArrayList<BuyDataBeans>();
		try {
			//DBに接続
			con = DBManager.getConnection();
			//SELECT文を準備
			String sql = "SELECT * FROM t_buy"
					+ " JOIN m_delivery_method"
					+ " ON t_buy.delivery_method_id = m_delivery_method.id"
					+ " WHERE t_buy.user_id = " + userId;
			//Statementを宣言する
			Statement st = con.createStatement();
			//SELECTを実行し、結果表を取得
			ResultSet rs = st.executeQuery(sql);

			//sqlをコンソールに出力
			System.out.println(sql);
			//結果表に格納された内容をBuyDataBeansインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				BuyDataBeans bdb = new BuyDataBeans();

				bdb.setId(rs.getInt("id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDeliveryMethodId(rs.getInt("delivery_method_id"));
				bdb.setDeliveryMethodName(rs.getString("name"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));

				buyList.add(bdb);
			}
			System.out.println(buyList);
			System.out.println("searching BuyDataBeans by userID has been completed");

			return buyList;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
			}
		}
	}

	/**
	 * URLから受け取ったパラメータbuyIdによる購入情報検索
	 * @param buyId
	 * @return BuyDataBeans
	 * 			UserBuyHistoryDetailに遷移したときの購入情報のデータ
	 * @throws SQLException
	 * 			呼び出し元にスローさせるため
	 */
	public static BuyDataBeans getBuyDataBeans(int buyId) throws SQLException{
		Connection con = null;
		BuyDataBeans bdb = new BuyDataBeans();
		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM t_buy"
					+ " JOIN m_delivery_method"
					+ " ON t_buy.delivery_method_id = m_delivery_method.id"
					+ " WHERE t_buy.id = " + buyId;
			//Statementを宣言する
			Statement st = con.createStatement();
			//SELECTを実行し、結果表を取得
			ResultSet rs = st.executeQuery(sql);
			//sqlをコンソールに出力
			System.out.println(sql);
			//結果表に格納された内容をBuyDataBeansインスタンスに追加
			if(!rs.next()) {
				return null;
			}
			bdb.setId(rs.getInt("id"));
			bdb.setUserId(rs.getInt("user_id"));
			bdb.setTotalPrice(rs.getInt("total_price"));
			bdb.setBuyDate(rs.getTimestamp("create_date"));
			bdb.setDeliveryMethodId(rs.getInt("delivery_method_id"));
			bdb.setDeliveryMethodName(rs.getString("name"));
			bdb.setDeliveryMethodPrice(rs.getInt("price"));

			return bdb;

		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
			}
		}
	}
}
