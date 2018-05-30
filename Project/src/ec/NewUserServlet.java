package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//newUser.jspにフォワード
		request.getRequestDispatcher(EcHelper.NEW_USER_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String password2 = request.getParameter("passwordcheck");
		String birthDate = request.getParameter("birthDate");
		//すでに登録されているログインIDが入力された場合
		UserDao UserDao = new UserDao();
		if(!UserDao.findByRegistration(loginId)) {
			//リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "すでに登録されたログインIDです");
			//newUser.jspにフォワード
			request.getRequestDispatcher(EcHelper.NEW_USER_PAGE).forward(request, response);
			return;
		}
		//パスワードとパスワード（確認）の入力内容が異なる場合
		if(!password.equals(password2)) {
			//リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "パスワードとパスワード（確認）の入力内容が異なります");
			//newUser.jspにフォワード
			request.getRequestDispatcher(EcHelper.NEW_USER_PAGE).forward(request, response);
			return;
		}
		//入力項目に一つでも未入力の項目がある場合
		if(loginId.equals("") || name.equals("") || address.equals("") || password.equals("") || password2.equals("") || birthDate.equals("")) {
			//リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "未入力の項目があります");
			//newUser.jspにフォワード
			request.getRequestDispatcher(EcHelper.NEW_USER_PAGE).forward(request, response);
			return;
		}
		UserDao userDao = new UserDao();
		userDao.InsertNewUserInfo(loginId, name, address, password2, birthDate);
		//ユーザ一覧にリダイレクト
		response.sendRedirect("UserAllServlet");
	}

}
