package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDao;

/**
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
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
		if(userInfo == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		//URLからidを取得し、Idを初期化する
		String Id = request.getParameter("id");
		//確認用
		System.out.println(Id);
		//Idを引数にして、Idに紐づくユーザー情報を出力する
		UserDao UserDao = new UserDao();
		UserDataBeans user = UserDao.findByUserInfoWithId(Id);

		//ユーザ情報をリクエストスコープにセットして、jspにフォワード
		request.setAttribute("user", user);
		request.getRequestDispatcher(EcHelper.USER_MORE_DETAIL_PAGE).forward(request, response);

	}
}
