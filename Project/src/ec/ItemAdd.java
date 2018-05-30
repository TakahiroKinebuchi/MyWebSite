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
 * 商品追加画面
 * @author dynabook
 */
@WebServlet("/ItemAdd")
public class ItemAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		try {
			//選択された商品のIDを型変換し、利用
			int id = Integer.parseInt(request.getParameter("item_id"));
			//対象のアイテム情報を取得
			ItemDataBeans item = ItemDao.getItemByItemID(id);

			//追加した商品を表示するためにリクエストパラメータにセット
			request.setAttribute("item", item);

			//カートを取得
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");

			//セッションにカートがなければ、カートを生成する
			if(cart == null) {
				cart = new ArrayList<ItemDataBeans>();
			}
			//カートに商品を追加
			cart.add(item);
			//カート情報更新
			session.setAttribute("cart", cart);
			request.setAttribute("cartActionMessage", "商品を追加しました");
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
