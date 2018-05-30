package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import beans.UserDataBeans;
import dao.ItemDao;

/**
 * 商品検索結果画面
 */
@WebServlet("/ItemSearchResult")
public class ItemSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 8;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//ログインセッションがない場合、ログイン画面にリダイレクトさせる
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		if(userInfo == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		try{
			String searchWord = request.getParameter("search_word");
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
			//新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchWord", searchWord);

			//商品リストを取得 ページ表示分のみ
			ArrayList<ItemDataBeans> searchResultItemList = ItemDao.getItemsByItemName(searchWord, pageNum, PAGE_MAX_ITEM_COUNT, "");

			//検索ワードに対しての総ページ数を取得
			double itemCount = ItemDao.getItemCount(searchWord);
			int pageMax = (int)Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			//総アイテム数
			request.setAttribute("itemCount", (int)itemCount);
			//総ページ数
			request.setAttribute("pageMax", pageMax);
			// キーワード
			request.setAttribute("search_word", searchWord);
			//表示ページ
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("itemList", searchResultItemList);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/itemsearchresult.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try{
		String sort = request.getParameter("sort");
		String searchWord = request.getParameter("search_word");

		//商品リストを取得 ページ表示分のみ
		ArrayList<ItemDataBeans> searchResultItemList = ItemDao.getItemsByItemName(searchWord, 1, PAGE_MAX_ITEM_COUNT, sort);

		//検索ワードに対しての総ページ数を取得
		double itemCount = ItemDao.getItemCount(searchWord);
		int pageMax = (int)Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

		//総アイテム数
		request.setAttribute("itemCount", (int)itemCount);
		//総ページ数
		request.setAttribute("pageMax", pageMax);
		// キーワード
		request.setAttribute("search_word", searchWord);
		//表示ページ
		request.setAttribute("pageNum", 1);
		request.setAttribute("itemList", searchResultItemList);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/itemsearchresult.jsp");
		dispatcher.forward(request, response);

		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}


	}
}
