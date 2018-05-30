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
import beans.DeliveryMethodDataBeans;
import beans.ItemDataBeans;
import dao.DeliveryMethodDao;

/**
 * Servlet implementation class BuyConfirmServlet
 */
@WebServlet("/BuyConfirmServlet")
public class BuyConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択された配送方法情報を受け取る
			int DeliveryMethodId = Integer.parseInt(request.getParameter("delivery_method_id"));
			//選択されたIDをもとに配送方法Beansを取得
			DeliveryMethodDataBeans userSelectDMB = DeliveryMethodDao.getDeliveryMethodDataBeansByID(DeliveryMethodId);
			//買い物かご
			@SuppressWarnings("unchecked")
			ArrayList<ItemDataBeans> cartIDBList = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
			//合計金額
			int totalPrice = EcHelper.getTotalItemPrice(cartIDBList);

			BuyDataBeans bdb = new BuyDataBeans();
			bdb.setUserId((int)session.getAttribute("userId"));
			bdb.setTotalPrice(totalPrice + userSelectDMB.getPrice());
			bdb.setDeliveryMethodId(userSelectDMB.getId());
			bdb.setDeliveryMethodName(userSelectDMB.getName());
			bdb.setDeliveryMethodPrice(userSelectDMB.getPrice());

			//購入確定で利用
			session.setAttribute("bdb", bdb);
			//フォワード
			request.getRequestDispatcher(EcHelper.BUY_CONFIRM_PAGE).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
