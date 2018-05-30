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
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;
import dao.BuyDao;
import dao.BuyDetailDao;

/**
 * Servlet implementation class BuyResultServlet
 */
@WebServlet("/BuyResultServlet")
public class BuyResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyResultServlet() {
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
			//セッションからカート情報を取得
			@SuppressWarnings("unchecked")
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) EcHelper.cutSessionAttribute(session, "cart");

			BuyDataBeans bdb = (BuyDataBeans)EcHelper.cutSessionAttribute(session, "bdb");

			//購入情報を登録
			int buyId = BuyDao.insertBuy(bdb);
			//購入詳細情報を購入情報IDに紐づけして登録
			for(ItemDataBeans cartInItem : cart) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setItemId(cartInItem.getId());
				BuyDetailDao.insertBuyDetail(bddb);
			}

			//購入完了ページ表示
			ArrayList<BuyDataBeans> resultBDB = BuyDao.getBuyDataBeansByBuyId(buyId);
			request.setAttribute("resultBDB", resultBDB);
			//購入アイテム情報
			ArrayList<ItemDataBeans> buyIDBList = BuyDetailDao.getItemDataBeansListByBuyId(buyId);
			request.setAttribute("buyIDBList", buyIDBList);
			//購入完了ページにフォワード
			request.getRequestDispatcher(EcHelper.BUY_RESULT_PAGE).forward(request, response);

		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
