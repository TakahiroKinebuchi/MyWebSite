<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	page import="beans.ItemDataBeans"%>
<%@	page import="beans.DeliveryMethodDataBeans"%>
<%@	page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/buy.css">

<meta charset="UTF-8">
<title>購入</title>
<%
	ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
	ArrayList<DeliveryMethodDataBeans> dmdbList = (ArrayList<DeliveryMethodDataBeans>) request.getAttribute("dmdbList");
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
						<form action="BuyConfirmServlet" method="post">
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
											<td class="center"><%=cartInItem.getName()%></td>
											<td class="center"><%=cartInItem.getPrice() %>円（税込）</td>
											<td class="center"><%=cartInItem.getPrice() %>円（税込）</td>
										</tr>
										<%
											}
										%>
										<tr>
											<td class="center"></td>
											<td class="center"></td>
											<td class="center">
												<div class="input-field col s8 offset-s2 ">
													<select name="delivery_method_id">
														<%
															for (DeliveryMethodDataBeans dmdb : dmdbList) {
														%>
														<option value="<%=dmdb.getId()%>"><%=dmdb.getName()%></option>
														<%
															}
														%>
													</select> <label>配送方法（クイックチャージ：最短1日、ノーマルチャージ：最短3日でお届け致します）</label>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
									<button class="btn btn-primary btn-block" type="submit">購入確認</button>
						</form>
					</div>
	</body>
</html>