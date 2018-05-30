package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import beans.UserDataBeans;
import dao.BuyDao;
import dao.BuyDetailDao;

/**
 * Servlet implementation class UserBuyHistoryDetailServlet
 */
@WebServlet("/UserBuyHistoryDetailServlet")
public class UserBuyHistoryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBuyHistoryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションを開始
		HttpSession session = request.getSession();
		//ログインセッションがない場合、ログイン画面にリダイレクトさせる
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		if(userInfo == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		try {
			//URLからGETパラメータとしてbuyIdを受け取る
			int buyId = Integer.parseInt(request.getParameter("buy_id"));
			//BuyDAOのgetBuyDataBeansByBuyIdを実行し、戻り値をbuyListに収納
			BuyDataBeans bdb = BuyDao.getBuyDataBeans(buyId);
			ArrayList<ItemDataBeans> buyIDBList = BuyDetailDao.getItemDataBeansListByBuyId(buyId);

			//入力された内容に誤りがあった場合に表示するエラーメッセージを格納する
			String validationMessage = (String)EcHelper.cutSessionAttribute(session, "validationMessage");

			request.setAttribute("validationMessage", validationMessage);
			request.setAttribute("buyIDBList", buyIDBList);
			request.setAttribute("bdb", bdb);

			//userbuyhistorydetail.jspにフォワード
			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);
		} catch(SQLException e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
