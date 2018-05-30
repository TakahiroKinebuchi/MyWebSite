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

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
			@SuppressWarnings("unchecked")
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
			//セッションにない場合、カートを作成
			if(cart == null) {
				cart = new ArrayList<ItemDataBeans>();
				session.setAttribute("cart", cart);
			}

			String cartActionMessage = "";
			//カートに商品が入っていないなら、cartActionMessageに「カートに商品がありません」
			if(cart.size() == 0) {
				cartActionMessage = "カートに商品がありません";
			}

			request.setAttribute("cartActionMessage", cartActionMessage);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
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
		HttpSession session = request.getSession();
		try {
			@SuppressWarnings("unchecked")
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
			//セッションにない場合、カートを作成
			if(cart == null) {
				cart = new ArrayList<ItemDataBeans>();
				session.setAttribute("cart", cart);
			}

			String cartActionMessage = "";
			//カートに商品が入っていないなら、cartActionMessageに「カートに商品がありません」
			if(cart.size() == 0) {
				cartActionMessage = "カートに商品がありません";
			}

			request.setAttribute("cartActionMessage", cartActionMessage);

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
