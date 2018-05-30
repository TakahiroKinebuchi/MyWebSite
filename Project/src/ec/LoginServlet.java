package ec;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け解消
		request.setCharacterEncoding("UTF-8");
		try {
		//リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		//リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		int userId = userDao.getUserId(loginId, password);

		//userIdをもとに、UserDataBeansを取得する
		UserDataBeans userInfo = new UserDataBeans();
		userInfo = UserDao.getUserDataBeansByUserId(userId);

		/** テーブルに該当のデータが見つからなかった場合 **/
		if (userId == 0) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "ログインに失敗しました。ログインIDまたはパスワードが異なります。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		/** テーブルに該当のデータが見つかった場合 **/
		// セッションにisLoginとuserIdの情報をセット
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("userInfo", userInfo);

		// TOPページのサーブレットにリダイレクト
		response.sendRedirect("IndexServlet");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
