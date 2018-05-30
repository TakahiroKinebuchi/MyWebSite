package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import base.DBManager;
import beans.ItemDataBeans;

public class ItemDao {

	// インスタンスオブジェクトを返却させてコードの簡略化
	public static ItemDao getInstance() {
		return new ItemDao();
	}

	/**
	 * ランダムで引数指定分のItemDataBeansを取得
	 * @param limit 取得したいかず
	 * @return <ItemDataBeans>
	 * @throws SQLException
	 */
	public static ArrayList<ItemDataBeans> getRandItem(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM m_item ORDER BY RAND() LIMIT ? ");
			st.setInt(1, limit);

			ResultSet rs = st.executeQuery();

			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans item = new ItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setCaffeine(rs.getInt("caffeine"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
				itemList.add(item);
			}
			System.out.println("getAllItem completed");
			return itemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 商品IDによる商品検索
	 * @param itemId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static ItemDataBeans getItemByItemID(int itemId) throws SQLException {
		Connection con = null;
		try {
			//DBに接続
			con = DBManager.getConnection();
			//SELECT文を準備
			String sql = "SELECT * FROM m_item WHERE id = ?";
			//SELECTを実行し、結果表を取得
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, itemId);
			ResultSet rs = st.executeQuery();

			//return文の準備として、ItemDataBeansのインスタンスを用意
			ItemDataBeans item = new ItemDataBeans();
			//主キーに紐づくレコードは1件だけなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			//itemの各アクセサに取得したid,name,detail,caffeine,price,file_nameをセットする
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setDetail(rs.getString("detail"));
			item.setCaffeine(rs.getInt("caffeine"));
			item.setPrice(rs.getInt("price"));
			item.setFileName(rs.getString("file_name"));
			//コンソールに、引数でitemを取得完了したことを出力する
			System.out.println("searching item by itemID has been completed");

			return item;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	/**
	 * 商品検索
	 * @param searchWord
	 * @param pageNum
	 * @param pageMaxItemCount
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<ItemDataBeans> getItemsByItemName(String searchWord, int pageNum, int pageMaxItemCount, String sortType) throws SQLException {
		Connection con = null;
		ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		try {
			//DBに接続
			con = DBManager.getConnection();
			//表示開始インデックスを宣言、初期化
			int startiItemNum = (pageNum - 1) * pageMaxItemCount;

			//SELECT文を準備
			String sql = "SELECT * FROM m_item";


				//商品名検索の場合
				sql += " WHERE name LIKE '%" + searchWord + "%' ";

			switch (sortType) {
				case "priceAsc":
					sql += " ORDER BY price asc";
					break;

				case "priceDesc":
					sql += " ORDER BY price desc";
					break;

				case "caffeineAsc":
					sql +=  " ORDER BY caffeine asc";
					break;

				case "caffeineDesc":
					sql += " ORDER BY caffeine desc";
					break;

				default:
					sql +=" ORDER BY id ASC";
			}

			sql += " LIMIT " + startiItemNum + "," + pageMaxItemCount;

			System.out.println(sql);




			//Statementを宣言
			Statement st = con.createStatement();
			//SELECTを実行し、結果表を取得
			ResultSet rs = st.executeQuery(sql);
			System.out.println(sql);

			System.out.println(rs);
			while (rs.next()) {
				ItemDataBeans item = new ItemDataBeans();

				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setCaffeine(rs.getInt("caffeine"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));

				itemList.add(item);
			}
			System.out.println("get Items by itemName has been completed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return itemList;
	}
	/**
	 * 商品総数を取得
	 *
	 * @param searchWord
	 * @return
	 * @throws SQLException
	 */
	public static double getItemCount(String searchWord) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from m_item where name like ?");
			st.setString(1, "%" + searchWord + "%");
			ResultSet rs = st.executeQuery();
			double coung = 0.0;
			while (rs.next()) {
				coung = Double.parseDouble(rs.getString("cnt"));
			}
			return coung;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	/**
	 * 全てのアイテム情報を取得する
	 * @return ArrayList<ItemDataBeans>
	 */
	public ArrayList<ItemDataBeans> findAllItem() {
		Connection conn = null;
		ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		try {
			//DBへ接続
			conn = DBManager.getConnection();
			//SELECT文を準備
			String sql = "SELECT * FROM m_item";
			//SQLを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//結果表に格納されたレコードの内容をItemDataBeansインスタンスに追加
			while(rs.next()) {
				ItemDataBeans item = new ItemDataBeans();

				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setCaffeine(rs.getInt("caffeine"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));

				itemList.add(item);
			}
			System.out.println("get all items completed");
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			//DB切断
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return itemList;
	}
	/***
	 * 商品検索マスター画面
	 * @param searchWord
	 * @return itemList
	 * @throws SQLException
	 */
	public static ArrayList<ItemDataBeans> getItemsByNameAtMaster(String searchWord) throws SQLException{
		Connection con = null;
		ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		try {
			//DBに接続
			con = DBManager.getConnection();
			//SELECT文を準備
			String sql = "SELECT * FROM m_item";

			if(!searchWord.equals("")) {
				sql += " WHERE name LIKE '%" + searchWord + "%'";
			}

			//確認用
			System.out.println(sql);
			//Statementを宣言
			Statement st = con.createStatement();
			//sqlを実行し、結果表を取得
			ResultSet rs = st.executeQuery(sql);
			//確認用
			System.out.println(sql);
			System.out.println(rs);
			//検索結果をコンストラクタにセット
			while(rs.next()) {
				ItemDataBeans item = new ItemDataBeans();

				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setCaffeine(rs.getInt("caffeine"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));

				itemList.add(item);
			}
			System.out.println("get Items by Name has been completed");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if(con != null) {
				con.close();
			}
		}
		return itemList;
	}
	/**
	 * 商品更新マスター画面
	 * @param itemId
	 * @param price
	 */
	public void UpdateItemInfo(int itemId, int price) {
		Connection conn = null;
		try {
			//DBへ接続
			conn = DBManager.getConnection();
			//UPDATE文を準備
			String sql = "UPDATE m_item SET price=? WHERE id=?";
			//sqlの?に引数をセットする
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, price);
			pStmt.setInt(2, itemId);
			//sql実行
			int result = pStmt.executeUpdate();
			//追加された行数を出力
			System.out.println(result);
			pStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//DBを切断
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
	 * 商品削除マスター画面
	 * @param itemId
	 */
	public void DeleteItemInfo(int itemId) {
		Connection conn = null;
		try {
			//DBへ接続
			conn = DBManager.getConnection();
			//DELETE文を準備
			String sql = "DELETE FROM m_item WHERE id=?";
			//sqlの?に引数をセット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, itemId);
			//sql実行
			int result = pStmt.executeUpdate();
			//追加された行数を出力
			System.out.println(result);
			pStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//DBを切断
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
	 * 新規商品登録マスター画面
	 * @param name
	 * @param detail
	 * @param caffeine
	 * @param price
	 * @param file_name
	 */
	public void InsertNewItemInfo(String name, String detail, int caffeine, int price, String fileName) {
		Connection conn = null;
		try {
			//DBへ接続
			conn = DBManager.getConnection();
			//INSERT文を準備
			String sql = "INSERT INTO m_item(name, detail, caffeine, price, file_name) VALUES(?, ?, ?, ?, ?)";
			//sqlの?に引数をセットする
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, detail);
			pStmt.setInt(3, caffeine);
			pStmt.setInt(4, price);
			pStmt.setString(5, fileName);
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
