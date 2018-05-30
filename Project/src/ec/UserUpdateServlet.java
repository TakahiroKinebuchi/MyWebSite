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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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
		//URLからGETパラメータとしてIDを受け取る
		String Id = request.getParameter("id");
		//確認用
		System.out.println(Id);
		//Idを引数にして、Idに紐づくユーザ情報を取得する
		UserDao UserDao = new UserDao();
		UserDataBeans user = UserDao.findByUserInfoWithId(Id);
		//ユーザ情報をリクエストスコープにセットして、jspにフォワード
		request.setAttribute("user", user);
		request.getRequestDispatcher(EcHelper.USER_UPDATE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの入力項目を取得
		String Id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		String password2 = request.getParameter("passwordcheck");
		String birthDate = request.getParameter("birthDate");
		//パスワードとパスワード（確認）がどちらも空欄の場合は、パスワードは更新せずにパスワード以外の項目を更新する。
		if(password.equals("") && password2.equals("")) {
			//パスワードは更新せずにパスワード以外の項目を更新するDaoを実行する
			UserDao UserDao = new UserDao();
			UserDao.UpdateUserInfoNotInPassword(Id, name, address, birthDate);

			//ユーザ一覧にリダイレクト
			response.sendRedirect("UserAllServlet");
			return;
		}
		//入力項目に一つでも未入力のものがある場合
		if(name.equals("") || address.equals("") || password.equals("") || password2.equals("") || birthDate.equals("")) {
			//リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "未入力の項目があります。");
			//userUpdate.jspにフォワード
			request.getRequestDispatcher(EcHelper.PRODUCT_UPDATE_PAGE).forward(request, response);
			return;
		}
		//Update実行
		UserDao UserDao = new UserDao();
		UserDao.UpdateUserInfo(Id, name, address, birthDate, password2);
		//ユーザ一覧にリダイレクト
		response.sendRedirect("UserAllServlet");
	}

}
