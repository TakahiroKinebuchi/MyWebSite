package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.BuyDao;
import dao.BuyDetailDao;
import dao.UserDao;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession();
		Object userInfo = session.getAttribute("userInfo");
		if (userInfo == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
			//URLからGETパラメータとしてIDを受け取る
			String Id = request.getParameter("id");
			//確認用
			System.out.println(Id);
			//Idを引数にして、Idに紐づくユーザ情報を出力する
			UserDao UserDao = new UserDao();
			UserDataBeans user = UserDao.findByUserInfoWithId(Id);
			//Idをint型userIdに変換し、userIdに紐づくArrayList<BuyDataBeans>を出力する
			int userId = Integer.parseInt(Id);
			//ユーザ情報と購入情報をリクエストスコープにセットして、userDelete.jspにフォワード
			request.setAttribute("user", user);
			request.getRequestDispatcher(EcHelper.USER_DELETE_PAGE).forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//URLからGETパラメータとしてidを受け取る
		String Id = request.getParameter("id");
		//確認用
		System.out.println(Id);
		//Idをint型に変える
		int userId = Integer.parseInt(Id);
		//削除を実行する
		BuyDetailDao BuyDetailDao = new BuyDetailDao();
		BuyDao BuyDao = new BuyDao();
		UserDao UserDao = new UserDao();


		BuyDetailDao.DeleteBuyDetailInfo(userId);
		BuyDao.DeleteBuyInfo(userId);
		UserDao.DeleteUserInfo(Id);
		//ユーザ情報にリダイレクト
		response.sendRedirect("UserAllServlet");
	}

}
