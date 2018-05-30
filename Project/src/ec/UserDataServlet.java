package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.UserDataBeans;
import dao.BuyDao;

/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション開始
		HttpSession session = request.getSession();
		//ログインセッションがない場合、ログイン画面にリダイレクトさせる
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		if(userInfo == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		try {
			//userInfoからidを取得し、userIdとする。
			int userId = userInfo.getId();
			//BuyDaoのgetBuyDataBeansByUserIdを実行し、戻り値をbuyListに格納
			ArrayList<BuyDataBeans> buyList = BuyDao.getBuyDataBeansByUserId(userId);
			//入力された内容に誤りがあったときなどに表示するエラーメッセージを格納する
			String validationMessage = (String) EcHelper.cutSessionAttribute(session, "validationMessage");

			request.setAttribute("validationMessage", validationMessage);
			request.setAttribute("buyList", buyList);

			request.getRequestDispatcher(EcHelper.USER_BUY_DATA_PAGE).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
