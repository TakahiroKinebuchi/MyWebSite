package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDao;

/**
 * Servlet implementation class UserAllServlet
 */
@WebServlet("/UserAllServlet")
public class UserAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAllServlet() {
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
		//ユーザ情報を取得
		UserDao userDao = new UserDao();
		ArrayList<UserDataBeans> userList = userDao.findAll();

		//リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		//ユーザ一覧のjspにフォワード
		request.getRequestDispatcher(EcHelper.USER_ALL_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//検索処理全般;

		//文字化け解消
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの入力項目の取得
		String loginId = request.getParameter("login-id");
		String name = request.getParameter("user-name");
		String startDate = request.getParameter("date-start");
		String endDate = request.getParameter("date-end");

		//検索を実施
		UserDao userDao = new UserDao();
		ArrayList<UserDataBeans> userList = userDao.findSearch(loginId, name, startDate, endDate);

		//リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		//ユーザ一覧のjspにフォワード
		request.getRequestDispatcher(EcHelper.USER_ALL_PAGE).forward(request, response);
	}

}
