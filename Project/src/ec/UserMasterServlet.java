package ec;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class UserMasterServlet
 */
@WebServlet("/UserMasterServlet")
public class UserMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//adminのログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		if (!userInfo.getloginId().equals("admin")) {
			response.sendRedirect("LoginServlet");
			return;
		}
		try {
			//アイテム情報を取得
			ItemDao ItemDao = new ItemDao();
			ArrayList<ItemDataBeans> itemList = ItemDao.findAllItem();
			//リクエストスコープにセット
			request.setAttribute("itemList", itemList);
			//usermaster.jspにフォワード
			request.getRequestDispatcher(EcHelper.USER_MASTER_PAGE).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションに接続
		HttpSession session = request.getSession();
		try {
			//検索ワードをsearchWordにセット
			request.setCharacterEncoding("UTF-8");//文字化け用
			String searchWord = request.getParameter("search_word");
			//商品リストを取得
			ArrayList<ItemDataBeans> searchResultItemList = ItemDao.getItemsByNameAtMaster(searchWord);
			//表示ページ
			request.setAttribute("itemList", searchResultItemList);
			//usermaster.jspにフォワード
			request.getRequestDispatcher(EcHelper.USER_MASTER_PAGE).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
