package ec;

import java.io.IOException;

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
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
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
			//選択された商品のIDを型変換し、利用
			int itemId = Integer.parseInt(request.getParameter("item_id"));
			//対象のアイテム情報を取得
			ItemDataBeans item = ItemDao.getItemByItemID(itemId);
			//リクエストパラメータにセット
			request.setAttribute("item", item);
			//ProductDetail.jspにフォワード
			request.getRequestDispatcher(EcHelper.PRODUCT_DETAIL_PAGE).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
