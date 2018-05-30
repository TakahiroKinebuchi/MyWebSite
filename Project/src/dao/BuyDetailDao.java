package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;

public class BuyDetailDao {
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static BuyDetailDao getInstance() {
		return new BuyDetailDao();
	}
	/**
	 * 購入詳細情報登録処理
	 * @param bddb BuyDetailDataBeans
	 * @throws SQLException
	 * 			呼び出し元にスローさせるため
	 */
	public static void insertBuyDetail(BuyDetailDataBeans bddb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = (Connection) DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy_detail(buy_id, item_id) VALUES (?, ?)");
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getItemId());
			st.executeUpdate();
			System.out.println("inserting BuyDetail has been completed");
		} catch(SQLException e) {
			System.out.println(e.toString());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
			}
		}
	}
	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return ArrayList<BuyDetailDataBeans>
	 * @throws SQLException
	 */
	public ArrayList<BuyDetailDataBeans> getBuyDataBeansListByBuyId(int buyId) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT * FROM t_buy_detail WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<BuyDetailDataBeans> buyDetailList = new ArrayList<BuyDetailDataBeans>();

			while(rs.next()) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setId(rs.getInt("id"));
				bddb.setBuyId(rs.getInt("buy_id"));
				bddb.setItemId(rs.getInt("item_id"));
				buyDetailList.add(bddb);
			}
			System.out.println("searching BuyDataBeansList by BuyID has been completed");
			return buyDetailList;
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
	 * 購入IDによる購入詳細情報検索
	 * @param buyId
	 * @return buyDetailItemList ArrayList<ItemDataBeans>
	 * 			購入詳細情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 */
	public static ArrayList<ItemDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException{
		Connection con = null;
		ArrayList<ItemDataBeans> buyDetailItemList = new ArrayList<ItemDataBeans>();
		try {
			//DBに接続
			con = DBManager.getConnection();
			//SELECT文を準備
			String sql = "SELECT m_item.id,"
					+ " m_item.name,"
					+ " m_item.price"
					+ " FROM t_buy_detail"
					+ " JOIN m_item"
					+ " ON t_buy_detail.item_id = m_item.id"
					+ " WHERE t_buy_detail.buy_id = " + buyId;
			//Statementを宣言する
			Statement st = con.createStatement();
			//SELECTを実行し、結果表を取得
			ResultSet rs = st.executeQuery(sql);
			//sqlをコンソールに出力
			System.out.println(sql);
			//結果表に格納された内容をBuyDataBeansインスタンスに設定し、ArrayListインスタンスに追加
			while(rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();

				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));

				buyDetailItemList.add(idb);
			}
			System.out.println("searching ItemDataBeansList by BuyID has been completed");
			return buyDetailItemList;
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
	 * userIdに紐づく購入詳細情報を消去する
	 * @param userId
	 */
	public void DeleteBuyDetailInfo(int userId) {
		Connection conn = null;
		try {
			//DBへ接続
			conn = DBManager.getConnection();
			//DELETE文を準備
			String sql = "DELETE am FROM t_buy_detail AS am INNER JOIN t_buy AS a ON am.buy_id = a.id WHERE a.user_id=?";
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
}
