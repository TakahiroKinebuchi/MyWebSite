package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.ItemDao;

/**
 * Servlet implementation class NewProductServlet
 */
@WebServlet("/NewProductServlet")
public class NewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//adminでのログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		if (!userInfo.getloginId().equals("admin")) {
			response.sendRedirect("LoginServlet");
			return;
		}
		//NewProduct.jspにフォワード
		request.getRequestDispatcher(EcHelper.NEW_PRODUCT_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの入力項目を取得
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		int caffeine = Integer.parseInt(request.getParameter("caffeine"));
		int price = Integer.parseInt(request.getParameter("price"));
		String fileName = request.getParameter("file_name");
		//Insert実行
		dao.ItemDao ItemDao = new ItemDao();
		ItemDao.InsertNewItemInfo(name, detail, caffeine, price, fileName);
		//UserMasterServletにリダイレクト
		response.sendRedirect("UserMasterServlet");
	}

}
