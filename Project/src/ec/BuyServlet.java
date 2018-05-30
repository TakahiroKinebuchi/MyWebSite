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

import beans.DeliveryMethodDataBeans;
import beans.ItemDataBeans;
import beans.UserDataBeans;
import dao.DeliveryMethodDao;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		if (userInfo == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション
		HttpSession session = request.getSession();
		try {
			UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");

			if (userInfo == null) {
				//Sessionにリターンページ情報を書き込む
				session.setAttribute("returnStrUrl", "BuyServlet");
				//LoginServletにリダイレクト
				response.sendRedirect("LoginServlet");
			} else if (cart.size() == 0) {
				request.setAttribute("cartActionMessage", "購入する商品がありません。");
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(EcHelper.CART_PAGE);
				dispatcher.forward(request, response);
			} else {
				//配送方法をDBから取得
				ArrayList<DeliveryMethodDataBeans> dMDBList = DeliveryMethodDao.getAllDeliveryMethodDataBeans();
				request.setAttribute("dmdbList", dMDBList);
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(EcHelper.BUY_PAGE);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
