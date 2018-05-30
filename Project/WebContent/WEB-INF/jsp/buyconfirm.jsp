<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ItemDataBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="beans.BuyDataBeans"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/buyconfirm.css">

<meta charset="UTF-8">
<title>購入確認</title>
<%
	ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
	BuyDataBeans bdb = (BuyDataBeans) session.getAttribute("bdb");
%>
</head>
<body>

	<div class="container">
		<ul class="nav justify-content-end">
			<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name}さん</a></li>
			<li class="nav-item"><a href="NewUserServlet"><img class="newuser" src="image/ユーザー新規.png" alt="ユーザー新規"></a></li>
			<li class="nav-item"><a href="LogoutServlet"><img class="logout" src="image/ログアウトアイコン.png" alt="ログアウトアイコン"></a></li>
		</ul>
	</div>

	<div class="container">
		<div class="row center">
			<h5 class=" col s12 light">カートアイテム</h5>
		</div>
							<div class="row">
								<table class="table">
									<thead>
										<tr>
											<th class="center" style="width: 20%">商品名</th>
											<th class="center">単価</th>
											<th class="center" style="width: 20%">小計</th>
										</tr>
									</thead>
									<tbody>
									<%
										for (ItemDataBeans cartInItem : cart) {
									%>
										<tr>
											<td class="center"><%=cartInItem.getName() %></td>
											<td class="center"><%=cartInItem.getPrice() %>円（税込）</td>
											<td class="center"><%=cartInItem.getPrice() %>円（税込）</td>
										</tr>
									<%
										}
									%>
										<tr>
											<th class="center">配送方法</th>
											<th class="center"></th>
											<th class="center">配送費</th>
										</tr>

										<tr>
											<td class="center"><%=bdb.getDeliveryMethodName() %></td>
											<td class="center"></td>
											<td class="center"><%=bdb.getDeliveryMethodPrice() %>円</td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<th class="center"></th>
											<th class="center">合計</th>
											<th class="center"><%=bdb.getTotalPrice() %>円</th>
										</tr>
									</tfoot>
								</table>
							</div>
		<form action="BuyResultServlet" method="post">
			<button class="btn btn-success btn-block" type="submit">購入</button>
		</form>
	</div>
	</body>
</html>